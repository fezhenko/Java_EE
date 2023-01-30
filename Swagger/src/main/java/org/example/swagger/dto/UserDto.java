package org.example.swagger.dto;

import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Jacksonized
@Value
public class UserDto {
    @NotEmpty
    Long userId;
    @NotEmpty
    String name;
    @NotEmpty
    String role;
    @NotEmpty
    Date createdAt;
}
