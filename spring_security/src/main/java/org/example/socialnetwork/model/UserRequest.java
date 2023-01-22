package org.example.socialnetwork.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Builder
@Value
@AllArgsConstructor
@Table("requests")
public class UserRequest {
    @Id
    @Column("request_id")
    Long requestId;
    @Column("request_user_id")
    Long requestUserId;
    @Column("received_user_id")
    Long receivedUserId;
    @Column("is_approved")
    Boolean isApproved;
    @Column("created_at")
    Date createdAt;
}

