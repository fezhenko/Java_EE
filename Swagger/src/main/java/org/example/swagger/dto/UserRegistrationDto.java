package org.example.swagger.dto;


import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

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
