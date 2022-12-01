package org.example.socialnetwork.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

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
