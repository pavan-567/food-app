package com.ganga.food_app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.entities.UserProfile;
import com.ganga.food_app.forms.UserForm;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.RoleService;
import com.ganga.food_app.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String login(@PathVariable(value = "error", required = false) String error, Principal principal,
            HttpSession session) {
        if (principal != null) {
            session.setAttribute("message", new Message("You are Already Logged In!", MessageType.WARNING));
            return "redirect:/items";
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            session.setAttribute("message", new Message("You are Already Logged In!", MessageType.WARNING));
            return "redirect:/items";
        }

        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,
            HttpSession session, HttpServletRequest request) {
        if (bindingResult.hasErrors())
            return "auth/register";

        String deliveryResult = request.getParameter("delivery");
        boolean isDelivery = deliveryResult == null ? false : true;

        User u = User.builder()
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .build();

        UserProfile up = UserProfile.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .gender(userForm.getGender())
                .phoneNumber(userForm.getPhoneNumber())
                .city(userForm.getCity())
                .state(userForm.getState())
                .country(userForm.getCountry())
                .user(u) // Important
                .build();

        if (isDelivery) {
            u.addRole(roleService.getDeliveryRole());
        } else {
            u.addRole(roleService.getUserRole());
        }

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
