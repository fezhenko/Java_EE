package org.example.apigateway.client.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Builder
@Value
@Jacksonized
public class AppUserDto {
    Long userId;
    String name;
    String password;
    String role;
    Date createdAt;
}
