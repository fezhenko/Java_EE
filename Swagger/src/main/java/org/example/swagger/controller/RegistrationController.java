package org.example.swagger.controller;

import lombok.RequiredArgsConstructor;
import org.example.swagger.dto.UserRegistrationDto;
import org.example.swagger.service.UserService;
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

    private final UserService userService;

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return "redirect:registration";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getUserFromRegistrationPage(
            @Valid @ModelAttribute("userRegistrationDto") final UserRegistrationDto userRegistrationDto,
            final BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:registration";
        }
        userService.createUser(
                userRegistrationDto.getUsername(),
                userRegistrationDto.getPassword(),
                userRegistrationDto.getRole());
        return "redirect:users";
    }
}
