package org.example.socialnetwork.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
public class UserRegistrationDto {
    @NotEmpty
    String username;
    @NotEmpty
    String password;
    @NotEmpty
    String role;
}
