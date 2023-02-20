package org.example.apigateway.controller;

import lombok.RequiredArgsConstructor;
import org.example.apigateway.client.dto.VerificationResultDto;
import org.example.apigateway.config.jwt.Jwt;
import org.example.apigateway.dto.AuthResultDto;
import org.example.apigateway.dto.CreateUserDto;
import org.example.apigateway.dto.CredentialsDto;
import org.example.apigateway.dto.UserDto;
import org.example.apigateway.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UsersController {

    private final UserService userService;
    private final Jwt jwt;

    @PostMapping("/auth")
    public ResponseEntity<AuthResultDto> getToken(@RequestBody CredentialsDto credentialsDto) {
        VerificationResultDto isUserValid = userService.userVerification(
                credentialsDto.getUsername(),
                credentialsDto.getPassword());

        if(!isUserValid.isValid()) {
            final String token = "Invalid credentials";
            return ResponseEntity.badRequest().body(new AuthResultDto(token));
        }

        final String token = jwt.generateToken(credentialsDto.getUsername());
        return ResponseEntity.accepted().body(new AuthResultDto(token));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(@RequestHeader (HttpHeaders.AUTHORIZATION) String token) {
        if (!token.isEmpty()) {
            List<UserDto> users = userService.findUsers();
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(users);
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@RequestHeader (HttpHeaders.AUTHORIZATION) String token,
                                                     @PathVariable("userId") Long userId) {
        if (!token.isEmpty()) {
            UserDto user = userService.getUserByUserId(userId);
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestHeader (HttpHeaders.AUTHORIZATION) String token,
                                              @RequestBody CreateUserDto createUserDto) {
        if (!token.isEmpty()) {
            return ResponseEntity.ok().body(userService.createUser(createUserDto));
        }
        return ResponseEntity.badRequest().build();
    }


}
