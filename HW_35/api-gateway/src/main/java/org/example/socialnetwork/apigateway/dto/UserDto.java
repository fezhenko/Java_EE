package org.example.socialnetwork.apigateway.dto;

import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;
@Value
@Jacksonized
public class UserDto {
    Long userId;
    String name;
    String role;
    Date createdAt;
}
