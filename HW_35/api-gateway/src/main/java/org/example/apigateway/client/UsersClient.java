package org.example.apigateway.client;


import org.example.apigateway.client.dto.AppUserDto;
import org.example.apigateway.client.dto.UserVerificationDto;
import org.example.apigateway.dto.CreateUserDto;
import org.example.apigateway.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "users-service", url = "${services.users-service.url}/api/v1/users")
public interface UsersClient {


    @RequestMapping(method = RequestMethod.POST, value = "/verify")
    UserVerificationDto verifyUserByCredentials(final UserVerificationDto credentialsToVerify);

    @RequestMapping(method = RequestMethod.GET)
    List<UserDto> findUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    AppUserDto findUserByUsername(@PathVariable("username") String username);

    @RequestMapping(method = RequestMethod.POST)
    UserDto createUser(@RequestBody final CreateUserDto createUserDto);

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    UserDto getUserByUserId(@PathVariable("userId") Long userId);
}
