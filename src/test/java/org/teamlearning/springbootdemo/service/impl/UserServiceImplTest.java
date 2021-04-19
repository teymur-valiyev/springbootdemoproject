package org.teamlearning.springbootdemo.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamlearning.springbootdemo.domain.dto.SignupDTO;
import org.teamlearning.springbootdemo.domain.dto.UserResponseDTO;
import org.teamlearning.springbootdemo.domain.dto.UserUpdateDTO;
import org.teamlearning.springbootdemo.domain.entities.Organizations;
import org.teamlearning.springbootdemo.domain.entities.User;
import org.teamlearning.springbootdemo.domain.mapper.UserMapper;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Resource
    private UserMapper userMapper;

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserMapper employeeService() {
            return new UserMapper() {
                @Override
                public void createOrganizationUser(SignupDTO signupDTO) {

                }

                @Override
                public void createOrganization(Organizations org) {

                }

                @Override
                public void createStaff(String userId, String organizationId) {

                }

                @Override
                public void deleteUser(int oid) {

                }

                @Override
                public void createUser(User user) {

                }

                @Override
                public UserResponseDTO getUserByName(String name) {
                    return null;
                }

                @Override
                public User getUserByNameAll(String name) {
                    return null;
                }

                @Override
                public void updateUser(int userId, UserUpdateDTO userUpdateDTO) {

                }
            };
        }
    }

    @BeforeEach
    void setUp() {
        UserResponseDTO dto = UserResponseDTO.builder()
                .name("john").build();
        Mockito.when(userMapper.getUserByName("john")).thenReturn(dto);
        User build = User.builder().build();
    }


    @org.junit.Test
    public void createOrganizationUser() {
        String name = "john";
        String surname = "doe";
        String email = "john.doe@mail.example";
        String role = "ADMIN";
        String pwd = "123123";
        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .role(role).build();

        userMapper.createUser(user);
        UserResponseDTO userResponseDTO = userMapper.getUserByName(name);
        assertThat(userResponseDTO.getName()).isEqualTo(name);
    }


}