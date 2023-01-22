package org.example.socialnetwork.dto;

import lombok.Value;

@Value
public class ApproveDeclineFriendRequestDto {
    Long requestedUserId;
    Long receivedUserId;
}
