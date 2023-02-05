package org.example.apigateway.service;

import lombok.RequiredArgsConstructor;
import org.example.apigateway.client.UsersClient;
import org.example.apigateway.dto.AuthResultDto;
import org.example.apigateway.dto.CreateUserDto;
import org.example.apigateway.dto.CredentialsDto;
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

    public AuthResultDto authorize(CredentialsDto credentialsDto) {
        return usersClient.authorize(credentialsDto);
    }

    public UserDto createUser(CreateUserDto createUserDto) {
       return usersClient.createUser(createUserDto);
    }
}
