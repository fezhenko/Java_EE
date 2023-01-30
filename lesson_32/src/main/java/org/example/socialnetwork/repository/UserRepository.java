package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findUsers();

    void saveUser(User user);

    User getUser(String username, String password);

    boolean validateUser(String name, String password);
}
