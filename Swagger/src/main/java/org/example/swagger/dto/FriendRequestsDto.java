package org.example.swagger.dto;

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
