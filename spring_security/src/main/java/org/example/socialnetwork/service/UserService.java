package org.example.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<AppUser> findUsers() {
        return userRepository.findUsers();
    }

    public AppUser createUser(String username, String password, String role) {
        if (validateUser(username)) {
            throw new RuntimeException("User with this username already exists");
        }
        userRepository.createUser(username, passwordEncoder.encode(password), role);
        return getUser(username);
    }

    public boolean validateUser(String username, String password) {
        AppUser appUser = userRepository.getUser(username);
        return passwordEncoder.matches(password, appUser.getPassword());
    }

    public boolean validateUser(String username) {
        return userRepository.validateUser(username);
    }

    public Long getUserId(String username, String password) {
        AppUser appUser = userRepository.getUser(username);
        if (passwordEncoder.matches(password, appUser.getPassword())) {
            return appUser.getUserId();
        }
        throw new RuntimeException("Invalid credentials!");
    }

    public AppUser getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }

    public AppUser getUser(String username) {
        return userRepository.getUser(username);
    }

    public AppUser getUser(String username, String password) {
        final AppUser appUser = userRepository.getUser(username);
        if (passwordEncoder.matches(password, appUser.getPassword())) {
            return appUser;
        }
        throw new RuntimeException("Invalid credentials!");
    }

    public AppUser getUser(String username, String password, String role) {
        final AppUser appUser = userRepository.getUser(username);
        if (passwordEncoder.matches(password, appUser.getPassword()) && appUser.getRole().equals(role)) {
            return appUser;
        }
        throw new RuntimeException("Invalid password or role");
    }
}
