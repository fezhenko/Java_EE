package org.example.socialnetwork.service;


import org.example.socialnetwork.model.User;
import org.example.socialnetwork.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public void saveUsers(User user) {
        userRepository.saveUser(user);
    }

    public boolean validateUser(String name, String password) {
        return userRepository.validateUser(name, password);
    }

    public User getUser(String name, String password) {
        return userRepository.getUser(name, password);
    }
}
