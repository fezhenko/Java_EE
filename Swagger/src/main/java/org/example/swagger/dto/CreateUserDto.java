package org.example.swagger.dto;


import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;

@Jacksonized
@Value
@Builder
public class CreateUserDto {

    @NotEmpty
    String username;
    @NotEmpty
    String password;
    @NotEmpty
    String role;
}
