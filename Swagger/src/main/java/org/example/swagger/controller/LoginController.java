package org.example.swagger.controller;

import lombok.AllArgsConstructor;
import org.example.swagger.dto.UserLoginDto;
import org.example.swagger.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


import javax.validation.Valid;


@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    final UserService userService;

    @GetMapping
    protected String getLoginForm(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());
        return "redirect:login";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String tryToLogin(
            @Valid @ModelAttribute("userLoginDto") final UserLoginDto userLoginDto, final BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:login";
        }
        if (userService.validateUser(userLoginDto.getUsername(), userLoginDto.getPassword())) {
            return "redirect:users";
        }
        return "redirect:registration";
    }
}
