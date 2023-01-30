package org.example.swagger.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class CreateFriendRequestDto {
    Long requestedUserId;
    Long receivedUserId;
}
