package org.example.apigateway.client.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserVerificationDto {
    String username;
    String password;

}
