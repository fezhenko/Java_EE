package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.config.jwt.Jwt;
import org.example.socialnetwork.dto.AuthResultDto;
import org.example.socialnetwork.dto.CredentialsDto;
import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService userService;
    private final Jwt jwt;

    @PostMapping
    public AuthResultDto authorize(@RequestBody final CredentialsDto credentials) {
        AppUser user = userService.getUser(credentials.getUsername(), credentials.getPassword());
        String token = jwt.generateToken(user.getName());
        return new AuthResultDto(token);
    }
}
