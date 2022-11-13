package org.example.springmvc.controller;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping
    protected String getUsers(Model model) {
        final List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users";
    }

}

