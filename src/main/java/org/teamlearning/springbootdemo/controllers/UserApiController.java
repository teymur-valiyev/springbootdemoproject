package org.teamlearning.springbootdemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.teamlearning.springbootdemo.domain.dto.SignupDTO;
import org.teamlearning.springbootdemo.domain.dto.UserDTO;
import org.teamlearning.springbootdemo.domain.dto.UserResponseDTO;
import org.teamlearning.springbootdemo.domain.dto.UserUpdateDTO;
import org.teamlearning.springbootdemo.service.UserService;

import javax.validation.Valid;


@RestController
@Slf4j
public class UserApiController implements UserApi {

    @Autowired
    private UserService userService;

    public ResponseEntity<Void> createOrganizationUser(@Valid @RequestBody SignupDTO body) {
        userService.createOrganizationUser(body);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> createUser(@RequestHeader(value = "admin", required = true) String admin, @RequestHeader(value = "password", required = true) String password, @Valid @RequestBody UserDTO body) {
        userService.createUser(admin, password, body);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserResponseDTO getUserByName(@PathVariable("name") String name) {
        UserResponseDTO userResponseDTO = userService.getUserByName(name);
        return userResponseDTO;
    }


    public ResponseEntity<Void> updateUser(@PathVariable("userId") int userId, @Valid @RequestBody UserUpdateDTO body) {
        userService.updateUser(userId, body);
        return ResponseEntity.ok().build();
    }

}
