package com.ganga.food_app.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ganga.food_app.entities.Role;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.repositories.RoleRepository;
import com.ganga.food_app.repositories.UserRepository;


@Controller
@RequiredArgsConstructor
public class DemoController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
            Role user = Role.builder().role("ROLE_USER").build();
            Role admin = Role.builder().role("ROLE_ADMIN").build();
            roleRepository.saveAll(List.of(user, admin));
        }
        return "redirect:/items";
    }

    @GetMapping("/loadResources")
    public String loadResources() {
        return "redirect:/items";
    }

    @GetMapping("/updateUser")
    public String updateUser(Principal principal) {
        User u = null;
        if(principal != null){ 
            u = userRepository.findByEmail(principal.getName()).orElse(null);
            if(u.getEmailToken().equals(null)) {
                u.setEmailToken(UUID.randomUUID());
            } else {
                u.setEmailToken(null);
            }
            userRepository.save(u);
        }
        return "redirect:/items";
    }
}
