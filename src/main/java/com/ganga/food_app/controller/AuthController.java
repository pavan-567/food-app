package com.ganga.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ganga.food_app.entities.Role;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.entities.UserProfile;
import com.ganga.food_app.forms.UserForm;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors())
            return "auth/register";

        User u = new User(userForm.getUsername(),
                userForm.getEmail(),
                userForm.getPassword());

        UserProfile up = new UserProfile(
                userForm.getFirstName(),
                userForm.getLastName(),
                userForm.getGender(),
                userForm.getPhoneNumber());

        Role r = new Role("ROLE_USER");
        u.addRole(r);

        up.setUser(u); // Important To Set
        u.setUserProfile(up);

        if (userForm.getGender().equals("male")) {
            up.setImage("/images/profile/male.png");
        } else {
            up.setImage("/images/profile/female.png");
        }

        session.setAttribute("message", new Message("Registration Successfull", MessageType.SUCCESS));
        
        userService.saveUser(u);
        return "redirect:/auth/register";
    }

}
