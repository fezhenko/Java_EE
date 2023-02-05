package org.example.apigateway.client;


import org.example.apigateway.dto.AuthResultDto;
import org.example.apigateway.dto.CreateUserDto;
import org.example.apigateway.dto.CredentialsDto;
import org.example.apigateway.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "users-service", url = "${services.users-service.url}/api/v1")
public interface UsersClient {


    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    AuthResultDto authorize(@RequestBody final CredentialsDto credentialsDto);

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    List<UserDto> findUsers();

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    UserDto createUser(@RequestBody final CreateUserDto createUserDto);
}
