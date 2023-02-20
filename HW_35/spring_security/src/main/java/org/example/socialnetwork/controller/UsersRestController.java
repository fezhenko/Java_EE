package org.example.socialnetwork.controller;

import lombok.AllArgsConstructor;
import org.example.socialnetwork.converter.UserConverter;
import org.example.socialnetwork.dto.*;
import org.example.socialnetwork.model.AppUser;
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
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping(value = "/verify",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VerificationResultDto verifyUserByCredentials(@RequestBody final UserVerificationDto credentialsToVerify) {

        boolean isUserVerified = userService.validateUser(
                credentialsToVerify.getUsername(),
                credentialsToVerify.getPassword());

        return VerificationResultDto.builder()
                .isValid(isUserVerified)
                .build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        final List<AppUser> appUsers = userService.findUsers();
        return ResponseEntity
                .ok(userConverter.toDto(appUsers));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        final AppUser appUser = userService.getUserById(userId);
        return ResponseEntity
                .ok(userConverter.toDto(appUser));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody final UserRegistrationDto userRegistrationDto) {
        AppUser appUser = userService.createUser(userRegistrationDto.getUsername(), userRegistrationDto.getPassword(),
                userRegistrationDto.getRole());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userConverter.toDto(appUser));
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUserDto> getFullUserDataByUsername(@PathVariable("username") String username) {
        AppUser appUser = userService.getUser(username);
        return ResponseEntity
                .ok().body(userConverter.toAppUserDto(appUser));
    }

}
