package org.example.socialnetwork.session;

import lombok.Data;

@Data
public class AuthContext {
    private boolean authorized;
    private Long authUserId;
}
