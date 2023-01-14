package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.AppUser;

import java.util.List;

public interface UserRepository {
    List<AppUser> findUsers();

    void createUser(String name, String password, String role);

    AppUser getUser(String username);

    boolean validateUser(String username);

    AppUser getUserById(Long userId);
}
