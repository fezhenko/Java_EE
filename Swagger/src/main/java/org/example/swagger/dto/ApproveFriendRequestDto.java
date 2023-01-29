package org.example.swagger.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class ApproveFriendRequestDto {
    Long requestedUserId;
    Long receivedUserId;
}
