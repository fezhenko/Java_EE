package org.example.springmvc.model;

import java.util.Date;

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

    public User(Long userId, String name, String role, String password, Date createdAt) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
