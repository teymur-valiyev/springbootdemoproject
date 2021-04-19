package org.teamlearning.springbootdemo.domain.objectmapper;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.teamlearning.springbootdemo.domain.dto.UserResponseDTO;
import org.teamlearning.springbootdemo.domain.entities.User;
import org.teamlearning.springbootdemo.domain.mapper.UserMapper;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test_createOrganizationUser_success() {
        String name = "john";
        String surname = "doe";
        String email = "john.doe@mail.example";
        String role = "ADMIN";
        String pwd = "123123";
        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
//                .psw(pwd)
                .role(role).build();

        userMapper.createUser(user);
        // Setup
        UserResponseDTO userResponseDTO = userMapper.getUserByName(name);
        // Execution
        // Verification
        assertThat(userResponseDTO.getName()).isEqualTo(name);
    }


}
