package org.example.socialnetwork.repository;

import org.example.socialnetwork.model.AppUser;

import java.util.List;

public interface UserRepository {
    List<AppUser> findUsers();

    void createUser(String name, String password, String role);

    AppUser getUser(String name, String role, String password);

    Long getUserId(String username, String password);

    boolean validateUser(String name, String password);

    boolean validateUsername(String username);

    AppUser getUserById(Long userId);

    AppUser getUser(String username);

    AppUser getUser(String username, String password);
}
