package org.example.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDto {
    private final String name;
    private final String password;
    private final String role;
}
