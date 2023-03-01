package org.example.socialnetwork.dto;

import lombok.Value;

import java.util.Date;

@Value
public class AppUserDto {
    Long userId;
    String name;
    String password;
    String role;
    Date createdAt;
}
