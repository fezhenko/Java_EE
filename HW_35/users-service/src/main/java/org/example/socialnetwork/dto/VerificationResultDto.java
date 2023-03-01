package org.example.socialnetwork.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VerificationResultDto {
    boolean isValid;

}
