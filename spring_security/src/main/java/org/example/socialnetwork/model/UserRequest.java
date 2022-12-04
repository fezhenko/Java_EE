package org.example.socialnetwork.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class UserRequest {
    private Long requestId;
    private Long requestUserId;
    private Long receivedUserId;
    private Boolean isApproved;
    private Date createdAt;
}

