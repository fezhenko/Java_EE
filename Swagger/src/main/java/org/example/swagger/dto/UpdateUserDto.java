package org.example.swagger.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Value
public class UpdateUserDto {
    @NotEmpty
    @Size(min = 1, max = 10)
    String username;
    @NotEmpty
    String role;
}
