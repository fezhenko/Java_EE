package org.example.socialnetwork.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginDto {
    @NotEmpty
    String name;
    @NotEmpty
    String password;
}
