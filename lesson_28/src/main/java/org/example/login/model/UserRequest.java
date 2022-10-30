package org.example.login.model;


import java.util.Date;

public class UserRequest {
    private Long id;
    private Long userId;
    private String requestStatus;
    private Date createdAt;

    public UserRequest(Long id, Long userId, String requestStatus, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.requestStatus = requestStatus;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
