package org.example.apigateway.dto;

import lombok.Value;

import java.util.Date;
@Value
public class UserDto {
    Long userId;
    String name;
    String role;
    Date createdAt;
}
