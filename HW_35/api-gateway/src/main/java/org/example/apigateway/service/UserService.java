package org.example.apigateway.service;

import lombok.RequiredArgsConstructor;
import org.example.apigateway.client.UsersClient;
import org.example.apigateway.client.dto.AppUserDto;
import org.example.apigateway.client.dto.UserVerificationDto;
import org.example.apigateway.client.dto.VerificationResultDto;
import org.example.apigateway.dto.CreateUserDto;
import org.example.apigateway.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersClient usersClient;

    public List<UserDto> findUsers() {
        return usersClient.findUsers();
    }

    public UserDto createUser(CreateUserDto createUserDto) {
       return usersClient.createUser(createUserDto);
    }

    public AppUserDto findUserByUsername(String username) {
        return usersClient.findUserByUsername(username);
    }

    public VerificationResultDto userVerification(String username, String password) {
        UserVerificationDto credentialsToVerify = UserVerificationDto.builder()
                .username(username)
                .password(password)
                .build();

        return usersClient.verifyUserByCredentials(credentialsToVerify);
    }

    public UserDto getUserByUserId(Long userId) {
        return usersClient.getUserByUserId(userId);
    }
}
