package org.teamlearning.springbootdemo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.teamlearning.springbootdemo.dto.*;
import org.teamlearning.springbootdemo.entities.Organizations;
import org.teamlearning.springbootdemo.entities.User;
import org.teamlearning.springbootdemo.exception.ApiValidationException;
import org.teamlearning.springbootdemo.mapper.UserMapper;
import org.teamlearning.springbootdemo.objectmapper.UserObjectMapper;
import org.teamlearning.springbootdemo.service.UserService;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public void createOrganizationUser(SignupDTO body) {
        Organizations organizations = UserObjectMapper.INSTANCE.getOrganizations(body);
        userMapper.createOrganization(organizations);

        User user = UserObjectMapper.INSTANCE.getUserFromSignupDTO(body);
        user.setRole("ADMIN");
        userMapper.createUser(user);

        log.info(String.valueOf(organizations.getId()));
        log.info(String.valueOf(user.getId()));
        userMapper.createStaff(String.valueOf(user.getId()), String.valueOf(organizations.getId()));
    }


    @Override
    public void createUser(String admin, String password, UserDTO body) {

        User userByNameAll = userMapper.getUserByNameAll(admin);
        String role = userByNameAll.getRole();

        if (role != null && role.equals("ADMIN") && userByNameAll.getPsw().equals(password)) {
            User user = UserObjectMapper.INSTANCE.getUserFromDTO(body);
            userMapper.createUser(user);
        } else {
            throw new ApiValidationException(
                    Collections.singletonList(new ErrorMessagesDTO("Only Admin can create users", "101"))
            );
        }
    }

    @Override
    public void deleteUser(int userId) {

        userMapper.deleteUser(userId);
    }

    @Override
    public UserResponseDTO getUserByName(String name) {
        UserResponseDTO userResponseDTO = userMapper.getUserByName(name);
        return userResponseDTO;
    }

    @Override
    public void updateUser(int userId, UserUpdateDTO body) {
        userMapper.updateUser(userId, body);
    }


}
