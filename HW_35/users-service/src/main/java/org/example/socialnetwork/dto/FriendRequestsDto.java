package org.example.socialnetwork.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FriendRequestsDto {
    Long requestId;
    Long requestUserId;
    Long receivedUserId;
    Boolean isApproved;
    Date createdAt;
}
