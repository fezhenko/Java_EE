package org.example.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Builder
@Value
@AllArgsConstructor
public class AppUser {
    Long userId;
    String name;
    String password;
    String role;
    Date createdAt;
}
