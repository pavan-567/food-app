package com.ganga.food_app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ganga.food_app.entities.Role;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.repositories.UserRepository;


@Controller
public class DemoController {

    @Autowired
    private UserRepository userRepository;
    

    @GetMapping("/")
    public String demoTest() {
        return "demo";
    }

    @GetMapping("/roleAdmn")
    public String makeUserAdmin(Model model) {
        User u = (User) model.getAttribute("loggedUser");
        u.addRole(new Role("ROLE_ADMIN"));
        userRepository.save(u);
        return "redirect:/profile";
    }

    @GetMapping("/loadResources")
    public String loadResources() {
        return "demo";
    }
}
