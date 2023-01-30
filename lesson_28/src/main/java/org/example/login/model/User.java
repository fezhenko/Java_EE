package org.example.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
@Getter
@AllArgsConstructor
public class User {

    private Long userId;
    private String name;
    private String role;
    private String password;
    private Date createdAt;

    public User(Long userId, String name, String role, Date createdAt) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String role, String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }
}
