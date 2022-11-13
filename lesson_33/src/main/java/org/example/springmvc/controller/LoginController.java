package org.example.springmvc.controller;

import lombok.AllArgsConstructor;
import org.example.springmvc.dto.UserLoginDto;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    final UserService userService;
    final AuthContext authContext;

    @GetMapping
    protected String getLoginForm() {
        return "login";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView tryToLogin(UserLoginDto userLoginDto) {
        if (userService.validateUser(userLoginDto.getName(), userLoginDto.getPassword())) {
            authContext.setAuthorized(true);
            authContext.setAuthUserId(userService.getUserId(userLoginDto.getName(), userLoginDto.getPassword()));
            return new RedirectView("users");
        }
        return new RedirectView("registration");
    }
}

