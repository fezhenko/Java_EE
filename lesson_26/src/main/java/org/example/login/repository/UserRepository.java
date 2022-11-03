package org.example.login.repository;

import org.example.login.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findUsers();

    void saveUser(User user);

    User getUser(String name);

    boolean validateUser(String name, String password);

}
