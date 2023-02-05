package org.example.apigateway.controller;

import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/v1/users")
public class UsersController {

    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<String> getToken(@RequestBody CredentialsDto credentialsDto) {
        String token = userService.authorize(credentialsDto).getToken();
        if(token.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        return ResponseEntity.accepted().body(token);
    }

    @GetMapping
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

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestHeader (HttpHeaders.AUTHORIZATION) String token,
                                              @RequestBody CreateUserDto createUserDto) {
        if (!token.isEmpty()) {
            return ResponseEntity.ok().body(userService.createUser(createUserDto));
        }
        return ResponseEntity.badRequest().build();
    }


}
