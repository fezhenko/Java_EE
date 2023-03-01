package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.model.AppUser;
import org.example.socialnetwork.service.UserService;
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

    @GetMapping
    protected String getUsers(Model model) {
        final List<AppUser> appUsers = userService.findUsers();
        model.addAttribute("users", appUsers);
        return "users";
    }

    protected void createUserFromRegistrationPage(String name, String role, String password) {
        if (userService.validateUser(name)) {
            new RedirectView("login");
            return;
        }
        try {
            userService.createUser(name, role, password);
        } catch (Exception ex) {
            new RedirectView("error");
        }
    }
}
