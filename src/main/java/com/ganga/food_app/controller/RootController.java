package com.ganga.food_app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.services.UserService;

@ControllerAdvice
public class RootController {
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInInformation(Model model, Principal principal) {
        User u = null;
        if (principal != null) {
            String email = principal.getName();
            u = userService.getUserByEmail(email).orElse(null);
        }
        model.addAttribute("loggedUser", u);
    }
}
