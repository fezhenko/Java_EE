package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    UserController userController;

    @Autowired
    public RegistrationController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping
    protected String getRegistrationPage(Model model) {
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return "registration";
    }


    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String getUserFromRegistrationPage(
            @Valid @ModelAttribute("userRegistrationDto") final UserRegistrationDto userRegistrationDto,
            final BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        userController.createUserFromRegistrationPage(userRegistrationDto.getName(), userRegistrationDto.getRole(),
                userRegistrationDto.getPassword());
        return ("redirect:users");
    }
}
