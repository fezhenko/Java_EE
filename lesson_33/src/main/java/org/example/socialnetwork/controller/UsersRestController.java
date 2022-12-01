package org.example.socialnetwork.controller;

import lombok.AllArgsConstructor;
import org.example.socialnetwork.converter.UserConverter;
import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.dto.UserRegistrationDto;
import org.example.socialnetwork.model.User;
import org.example.socialnetwork.service.UserService;
import org.example.socialnetwork.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UsersRestController {
    final UserService userService;
    final AuthContext authContext;
    final UserConverter userConverter;

    @GetMapping
    protected List<UserDto> getUsers() {
        final List<User> users = userService.findUsers();
        return userConverter.toDto(users);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    protected UserDto getUserFromRegistrationPage(@RequestBody @Valid final UserRegistrationDto userRegistrationDto) {
        createUser(userRegistrationDto.getName(), userRegistrationDto.getRole(),
                userRegistrationDto.getPassword());
        return userConverter.toDto(getCreatedUser(userRegistrationDto.getName(), userRegistrationDto.getRole()));
    }

    protected void createUser(String name, String role, String password) {
        if (!userService.validateUsername(name)) {
            userService.createUser(name, role, password);
        }
        throw new RuntimeException("user already exists");
    }

    protected User getCreatedUser(String name, String role) {
        if (!userService.validateUsername(name)) {
            return userService.getUser(name, role);
        }
        throw new RuntimeException("user does not exists");
    }
}
