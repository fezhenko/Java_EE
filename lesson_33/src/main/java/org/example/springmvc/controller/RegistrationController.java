package org.example.springmvc.controller;

import org.example.springmvc.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    UserController userController;

    @Autowired
    public RegistrationController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping
    protected String getRegistrationPage() {
        return "registration";
    }


    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView getUserFromRegistrationPage(UserRegistrationDto userRegistrationDto) {
        return userController.createUserFromRegistrationPage(userRegistrationDto);
    }
}
