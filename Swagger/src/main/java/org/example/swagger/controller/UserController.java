package org.example.swagger.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.swagger.model.AppUser;
import org.example.swagger.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUsers(final Model model) {
        final List<AppUser> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users";
    }

}
