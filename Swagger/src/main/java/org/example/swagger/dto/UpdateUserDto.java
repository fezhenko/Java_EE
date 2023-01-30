package org.example.swagger.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Value
@Jacksonized
@Builder
public class UpdateUserDto {
    @NotEmpty
    @Size(min = 1, max = 15)
    String username;
    @NotEmpty
    String role;
}
