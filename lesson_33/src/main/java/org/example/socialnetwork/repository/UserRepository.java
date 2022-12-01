package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findUsers();

    void createUser(String name, String password, String role);

    User getUser(String name, String role);

    Long getUserId(String username, String password);

    boolean validateUser(String name, String password);

    boolean validateUsername(String username);
}
