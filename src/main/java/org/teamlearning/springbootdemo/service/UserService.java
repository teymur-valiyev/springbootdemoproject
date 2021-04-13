package org.teamlearning.springbootdemo.service;


import org.teamlearning.springbootdemo.dto.SignupDTO;
import org.teamlearning.springbootdemo.dto.UserDTO;
import org.teamlearning.springbootdemo.dto.UserResponseDTO;
import org.teamlearning.springbootdemo.dto.UserUpdateDTO;


public interface UserService {
    void createOrganizationUser(SignupDTO body);
    void createUser(String admin, String password, UserDTO body);
    void deleteUser(int userId);
    UserResponseDTO getUserByName(String name);
    void updateUser(int userId, UserUpdateDTO body);
}
