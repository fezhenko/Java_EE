package org.example.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dto.UserRegistrationDto;
import org.example.socialnetwork.service.UserService;
import org.example.socialnetwork.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserController userController;
    private final UserService userService;
    private final AuthContext authContext;

    @GetMapping
    protected String getRegistrationPage(Model model) {
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String userRegistration(
            @Valid @ModelAttribute("userRegistrationDto") final UserRegistrationDto userRegistrationDto,
            final BindingResult result) {

        if (userService.validateUsername(userRegistrationDto.getName())) {
            new RedirectView("login");
            return "login";
        }

        if (result.hasErrors()) {
            return "registration";
        }

        try {
            userService.createUser(
                    userRegistrationDto.getName(),
                    userRegistrationDto.getPassword(),
                    userRegistrationDto.getRole());
            authContext.setAuthorized(true);
            authContext.setAuthUserId(userService.getUserId(userRegistrationDto.getName()));
        } catch (Exception ex) {
            new RedirectView("error");
        }
        userController.createUserFromRegistrationPage(userRegistrationDto.getName(), userRegistrationDto.getRole(),
                userRegistrationDto.getPassword());
        return "redirect:users";
    }
}
