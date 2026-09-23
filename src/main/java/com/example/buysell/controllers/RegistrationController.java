package com.example.buysell.controllers;

import com.example.buysell.Services.RegistrationService;
import com.example.buysell.exceptions.UserAlreadyExistsException;
import com.example.buysell.models.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        try {
            registrationService.register(user);
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
}