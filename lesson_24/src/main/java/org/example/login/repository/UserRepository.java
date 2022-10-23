package org.example.login.repository;

import org.example.login.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findUsers();

    void saveUser(User user);

    User getUser(User user);

    boolean validateUser(String name, String password);

}
