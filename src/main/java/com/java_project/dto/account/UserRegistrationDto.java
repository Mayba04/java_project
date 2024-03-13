package com.java_project.dto.account;


import lombok.Data;

@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private int roleId; 
}