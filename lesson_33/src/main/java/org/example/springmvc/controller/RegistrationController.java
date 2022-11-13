package org.example.springmvc.controller;

import lombok.AllArgsConstructor;
import org.example.springmvc.dto.UserRegistrationDto;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    final UserService userService;
    final AuthContext authContext;

    @GetMapping
    protected String getRegistrationPage() {
        return "registration";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView createUser(final UserRegistrationDto userRegistrationDto) {
        if (userService.validateUser(userRegistrationDto.getName(), userRegistrationDto.getPassword())) {
            return new RedirectView("login");
        }
        try {
            userService.createUser(userRegistrationDto.getName(), userRegistrationDto.getRole(),
                    userRegistrationDto.getPassword());
            authContext.setAuthorized(true);
            authContext.setAuthUserId(userService.getUserId(userRegistrationDto.getName(), userRegistrationDto.getPassword()));
            return new RedirectView("users");
        } catch (Exception ex) {
            return new RedirectView("error");
        }
    }
}
