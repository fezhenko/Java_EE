package org.example.socialnetwork.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginDto {
    @NotEmpty
    String username;
    @NotEmpty
    String password;
}
