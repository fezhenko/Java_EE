package org.example.springmvc.dto;

import lombok.Value;

@Value
public class UserRegistrationDto {
    String name;
    String password;
    String role;
}
