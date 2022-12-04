package org.example.socialnetwork.session;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AuthContext {
    private Long authUserId;
}
