package org.example.login.model;


import lombok.AllArgsConstructor;
import java.util.Date;

@AllArgsConstructor
public class UserRequest {
    private Long requestId;
    private Long requestUserId;
    private Long receivedUserId;
    private Boolean isApproved;
    private Date createdAt;
}

