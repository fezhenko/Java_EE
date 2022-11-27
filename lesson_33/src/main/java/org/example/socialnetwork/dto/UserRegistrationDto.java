package org.example.socialnetwork.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotEmpty
    String name;
    @NotEmpty
    String password;
    @NotEmpty
    String role;
}
