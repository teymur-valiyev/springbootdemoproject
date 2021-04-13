
package org.teamlearning.springbootdemo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.teamlearning.springbootdemo.dto.SignupDTO;
import org.teamlearning.springbootdemo.dto.UserDTO;
import org.teamlearning.springbootdemo.dto.UserResponseDTO;
import org.teamlearning.springbootdemo.dto.UserUpdateDTO;

import javax.validation.Valid;

@Validated
@RequestMapping(value = "/v1")
public interface UserApi {

    @RequestMapping(value = "/user/signup",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> createOrganizationUser(@Valid @RequestBody SignupDTO body);


    @RequestMapping(value = "/user",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> createUser(@RequestHeader(value = "admin", required = true) String admin, @RequestHeader(value = "password", required = true) String password, @Valid @RequestBody UserDTO body);


    @RequestMapping(value = "/user/{userId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId);


    @RequestMapping(value = "/user/{name}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    UserResponseDTO getUserByName(@PathVariable("name") String name);

    @RequestMapping(value = "/user/{userId}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(int userId, @Valid @RequestBody UserUpdateDTO body);

}
