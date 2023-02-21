package org.example.apigateway.service;

import lombok.RequiredArgsConstructor;
import org.example.apigateway.client.UsersClient;
import org.example.apigateway.client.dto.AppUserDto;
import org.example.apigateway.client.dto.UserVerificationDto;
import org.example.apigateway.client.dto.VerificationResultDto;
import org.example.apigateway.dto.CreateUserDto;
import org.example.apigateway.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersClient usersClient;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> findUsers() {
        return usersClient.findUsers();
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        CreateUserDto adjustedUserDto = CreateUserDto.builder()
                .username(createUserDto.getUsername())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .role(createUserDto.getRole())
                .build();
        return usersClient.createUser(adjustedUserDto);
    }

    public AppUserDto findUserByUsername(String username) {
        return usersClient.findUserByUsername(username);
    }

    public VerificationResultDto userVerification(String username, String password) {
        UserVerificationDto credentialsToVerify = UserVerificationDto.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        return usersClient.verifyUserByCredentials(credentialsToVerify);
    }

    public UserDto getUserByUserId(Long userId) {
        return usersClient.getUserByUserId(userId);
    }
}
