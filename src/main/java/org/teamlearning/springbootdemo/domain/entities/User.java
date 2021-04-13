package org.teamlearning.springbootdemo.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String createdAt;
    private String name;
    private String surname;
    private String email;
    private String psw;
    private String role;
}
