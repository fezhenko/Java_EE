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
@Table("users")
public class AppUser {
    @Id
    @Column("user_id")
    Long userId;
    @Column("name")
    String name;
    @Column("password")
    String password;
    @Column("role")
    String role;
    @Column("created_at")
    Date createdAt;
}
