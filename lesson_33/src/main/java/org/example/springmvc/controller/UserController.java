package org.example.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.dto.UserRegistrationDto;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    final AuthContext authContext;

    @GetMapping
    protected String getUsers(Model model) {
        final List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users";
    }

    protected RedirectView createUserFromRegistrationPage(final UserRegistrationDto userRegistrationDto) {
        if (userService.validateUsername(userRegistrationDto.getName())) {
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
