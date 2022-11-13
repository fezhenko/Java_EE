package org.example.springmvc.repository;

import org.example.springmvc.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findUsers();

    void createUser(String name, String password, String role);

    Long getUserId(String username, String password);

    boolean validateUser(String name, String password);
}
