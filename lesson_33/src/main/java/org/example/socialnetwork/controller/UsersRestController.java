package org.example.socialnetwork.controller;

import lombok.AllArgsConstructor;
import org.example.socialnetwork.converter.UserConverter;
import org.example.socialnetwork.dto.UserDto;
import org.example.socialnetwork.dto.UserRegistrationDto;
import org.example.socialnetwork.model.User;
import org.example.socialnetwork.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UsersRestController {
    final UserService userService;
    final UserConverter userConverter;

    @GetMapping
    protected ResponseEntity<List<UserDto>> getUsers() {
        final List<User> users = userService.findUsers();
        return ResponseEntity
                .ok(userConverter.toDto(users));
    }

    @GetMapping("/{userId}")
    protected ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        final User user = userService.getUserById(userId);
        return ResponseEntity
                .ok(userConverter.toDto(user));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    protected ResponseEntity<UserDto> createUser(@Valid @RequestBody final UserRegistrationDto userRegistrationDto) {
        userService.createUser(userRegistrationDto.getName(), userRegistrationDto.getPassword(),
                userRegistrationDto.getRole());
        User user = userService.getUser(userRegistrationDto.getName(), userRegistrationDto.getPassword(),
                userRegistrationDto.getRole());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userConverter.toDto(user));
    }
}
