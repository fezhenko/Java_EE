package org.example.apigateway.dto;

import lombok.Value;

@Value
public class CreateUserDto {
    String username;
    String password;
    String role;
}
