package org.example.socialnetwork.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
public class UserRegistrationDto {
    @NotEmpty
    String name;
    @NotEmpty
    String password;
    @NotEmpty
    String role;
}
