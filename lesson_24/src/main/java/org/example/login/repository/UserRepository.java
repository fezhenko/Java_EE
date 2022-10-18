package org.example.login.repository;

import org.example.login.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findUsers();

    void saveUser(User user);

    User getUser(User user);

    boolean validateUser(User user);

}
