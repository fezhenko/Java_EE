package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.UserRepository;
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

    public boolean validateUser(String name, String password) {
        return userRepository.validateUser(name, password);
    }

    public Long getUserId(String name, String password) {
        return userRepository.getUserId(name, password);
    }

}
