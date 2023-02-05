package org.example.socialnetwork.apigateway.dto;

import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
public class CreateUserDto {
    String username;
    String password;
    String role;
}
