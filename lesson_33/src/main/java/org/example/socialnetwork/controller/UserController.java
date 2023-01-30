package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.model.User;
import org.example.socialnetwork.service.UserService;
import org.example.socialnetwork.session.AuthContext;
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
        final List<User> appUsers = userService.findUsers();
        model.addAttribute("users", appUsers);
        return "users";
    }

    protected void createUserFromRegistrationPage(String name, String role, String password) {
        if (userService.validateUsername(name)) {
            new RedirectView("login");
            return;
        }
        try {
            userService.createUser(name, role, password);
            authContext.setAuthorized(true);
            authContext.setAuthUserId(userService.getUserId(name));
        } catch (Exception ex) {
            new RedirectView("error");
        }
    }


}
