package org.example.socialnetwork.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class UserVerificationDto {
    String username;
    String password;
}
