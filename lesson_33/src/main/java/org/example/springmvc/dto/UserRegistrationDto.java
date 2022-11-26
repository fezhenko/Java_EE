package org.example.springmvc.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class UserRegistrationDto {
    @NotEmpty
    String name;
    @NotEmpty
    String password;
    @NotEmpty
    String role;
}
