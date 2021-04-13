package org.teamlearning.springbootdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organizations {
    private int id;
    private String createdAt;
    private String name;
    private String address;
    private String phone;
}
