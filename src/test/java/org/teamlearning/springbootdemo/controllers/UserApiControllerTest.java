package org.teamlearning.springbootdemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.teamlearning.springbootdemo.domain.dto.SignupDTO;
import org.teamlearning.springbootdemo.domain.dto.UserDTO;
import org.teamlearning.springbootdemo.domain.dto.UserResponseDTO;
import org.teamlearning.springbootdemo.domain.dto.UserUpdateDTO;
import org.teamlearning.springbootdemo.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createOrganizationUser() throws Exception {

        SignupDTO user = SignupDTO.builder()
                .name("testmmc")
                .address("london")
                .phone("123789")
                .email("test@mail.com")
                .password("123456")
                .user("john")
                .build();

        mockMvc.perform(post("/v1/user/signup/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        UserResponseDTO john = userService.getUserByName("john");
        assertThat(user.getUser()).isEqualTo(john.getName());
    }

    @Test
    void createUser() throws Exception {
        SignupDTO signupDTO = SignupDTO.builder()
                .name("testmmc")
                .address("london")
                .phone("123789")
                .email("test@mail.com")
                .password("123456")
                .user("john")
                .build();

        userService.createOrganizationUser(signupDTO);

        UserDTO userDTO = UserDTO.builder()
                .name("ted")
                .surname("")
                .email("ted@mail.com")
                .password("123456")
                .status(1).build();

        mockMvc.perform(post("/v1/user/")
                .header("admin", signupDTO.getUser())
                .header("password", signupDTO.getPassword())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());

        UserResponseDTO user = userService.getUserByName(userDTO.getName());
        assertThat(user.getName()).isEqualTo(userDTO.getName());
    }

    @Test
    void deleteUser() throws Exception {
        SignupDTO signupDTO = SignupDTO.builder()
                .name("testmmc")
                .address("london")
                .phone("123789")
                .email("test@mail.com")
                .password("123456")
                .user("john")
                .build();

        userService.createOrganizationUser(signupDTO);
        UserResponseDTO user = userService.getUserByName(signupDTO.getUser());

        mockMvc.perform(delete("/v1/user/{userId}", user.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        user = userService.getUserByName(signupDTO.getName());
        assertThat(user).isNull();
    }

    @Test
    void getUserByName() throws Exception {

        SignupDTO signupDTO = SignupDTO.builder()
                .name("testmmc")
                .address("london")
                .phone("123789")
                .email("test@mail.com")
                .password("123456")
                .user("john")
                .build();

        userService.createOrganizationUser(signupDTO);

        mockMvc.perform(get("/v1/user/{name}", "john").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(signupDTO.getUser()));
    }

    @Test
    void updateUser() throws Exception {

        SignupDTO signupDTO = SignupDTO.builder()
                .name("testmmc")
                .address("london")
                .phone("123789")
                .email("test@mail.com")
                .password("123456")
                .user("john")
                .build();

        userService.createOrganizationUser(signupDTO);

        UserResponseDTO user = userService.getUserByName(signupDTO.getUser());

        UserUpdateDTO userUpdateDTO = UserUpdateDTO.builder()
                .name("teymur")
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        mockMvc.perform(put("/v1/user/{userId}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userUpdateDTO)))
                .andDo(print())
                .andExpect(status().isOk());

        UserResponseDTO updatedUser = userService.getUserByName(userUpdateDTO.getName());
        assertThat(updatedUser.getName()).isEqualTo(userUpdateDTO.getName());
    }
}