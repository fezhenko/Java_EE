package org.example.login.service;

import org.example.login.model.User;
import org.example.login.repository.UserRepository;

import java.util.List;

public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public void saveUsers(User user){
        userRepository.saveUser(user);
    }

    public boolean validateUser(User user)
    {
        return userRepository.validateUser(user);
    }

}
