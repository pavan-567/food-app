package com.ganga.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ganga.food_app.entities.Role;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.repositories.RoleRepository;
import com.ganga.food_app.repositories.UserRepository;


@Controller
public class DemoController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public String demoTest() {
        return "redirect:/items";
    }

    @GetMapping("/roleAdmn")
    public String makeUserAdmin(Model model) {
        User u = (User) model.getAttribute("loggedUser");
        u.addRole(roleRepository.getAdminRole());
        userRepository.save(u);
        return "redirect:/profile";
    }

    @GetMapping("/createRoles")
    public String createRoles() {

        List<Role> roles = roleRepository.findAll();
        boolean exists = false;
        for(var role: roles) {
            if(role.getRole().equals("ROLE_USER") || role.getRole().equals("ROLE_ADMIN")) {
                exists = true;
                break;
            }
        }
        if(!exists) {
            Role user = new Role("ROLE_USER");
            Role admin = new Role("ROLE_ADMIN");
            roleRepository.saveAll(List.of(user, admin));
        }
        return "redirect:/items";
    }

    @GetMapping("/loadResources")
    public String loadResources() {
        return "redirect:/items";
    }
}
