package org.example.apigateway.client;


import org.example.apigateway.dto.AuthResultDto;
import org.example.apigateway.dto.CreateUserDto;
import org.example.apigateway.dto.CredentalsDto;
import org.example.apigateway.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "main", url = "${services.main.url}/api/v1")
public interface MainClient {

    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    AuthResultDto authorize(@RequestBody final CredentalsDto credentalsDto);

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    List<UserDto> getUsers();

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    UserDto createUser(@RequestBody final CreateUserDto createUserDto);
}
