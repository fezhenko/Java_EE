package org.example.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.model.User;
import org.example.socialnetwork.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public void createUser(String name, String password, String role) {
        if (userRepository.validateUser(name, password)) {
            throw new RuntimeException("User already exists");
        }
        userRepository.createUser(name, password, role);
    }

    public User getUser(String name, String role) {
        return userRepository.getUser(name, role);
    }

    public boolean validateUser(String name, String password) {
        return userRepository.validateUser(name, password);
    }

    public boolean validateUsername(String username) {
        return userRepository.validateUsername(username);
    }

    public Long getUserId(String name, String password) {
        return userRepository.getUserId(name, password);
    }

}
