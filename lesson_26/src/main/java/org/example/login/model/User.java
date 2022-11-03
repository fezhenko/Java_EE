package org.example.login.model;

import java.util.Date;

public class User {

    private Long id;
    private String name;
    private String role;
    private String password;
    private Date createdAt;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String role, String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public User(Long id, String name, String role, String password, Date createdAt) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
