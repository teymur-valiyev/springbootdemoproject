package org.teamlearning.springbootdemo.domain.objectmapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.teamlearning.springbootdemo.domain.dto.SignupDTO;
import org.teamlearning.springbootdemo.domain.dto.UserDTO;
import org.teamlearning.springbootdemo.domain.entities.Organizations;
import org.teamlearning.springbootdemo.domain.entities.User;

public class UserObjectMapperTest {

    private UserObjectMapper userObjectMapper;

    @Before
    public void setup() {
        userObjectMapper = UserObjectMapper.INSTANCE;
    }

    @Test
    public void getOrganizationsTest() {
        SignupDTO signupDTO = SignupDTO.builder()
                .name("google").phone("123456")
                .address("london").user("john")
                .email("john@mail.test").password("333")
                .build();
        Organizations organizations = userObjectMapper.getOrganizations(signupDTO);
        Assert.assertEquals(organizations.getName(), signupDTO.getName());
        Assert.assertEquals(organizations.getAddress(), signupDTO.getAddress());
        Assert.assertEquals(organizations.getPhone(), signupDTO.getPhone());

    }

    @Test
    public void getUserFromSignupDTOTest() {
        SignupDTO signupDTO = SignupDTO.builder()
                .name("google").phone("123456")
                .address("london").user("john")
                .email("john").password("333")
                .build();
        User user = userObjectMapper.getUserFromSignupDTO(signupDTO);
        Assert.assertEquals(user.getName(), signupDTO.getUser());
        Assert.assertEquals(user.getEmail(), signupDTO.getEmail());
        Assert.assertEquals(user.getPsw(), signupDTO.getPassword());
    }

    @Test
    public void getUserFromDTOTest() {
        UserDTO userDTO = UserDTO
                .builder().name("john").surname("doe")
                .email("john@mail.test").password("333")
                .status(1).build();
        User userFromDTO = userObjectMapper.getUserFromDTO(userDTO);
        Assert.assertEquals(userFromDTO.getName(), userDTO.getName());
        Assert.assertEquals(userFromDTO.getEmail(), userDTO.getEmail());
        Assert.assertEquals(userFromDTO.getPsw(), userDTO.getPassword());
    }
}
