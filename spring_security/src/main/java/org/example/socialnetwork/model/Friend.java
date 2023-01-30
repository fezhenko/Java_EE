package org.example.socialnetwork.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table ("friends")
public class Friend {
    @Id
    @Column("id")
    Long id;
    @Column("friend_user_id")
    Long friendId;
    @Column("request_id")
    Long requestId;
    @Column("created_at")
    Date createdAt;
}
