package com.ganga.food_app.controller;

import java.security.Principal;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.entities.UserProfile;
import com.ganga.food_app.forms.UserForm;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.VerificationHelper;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.EmailService;
import com.ganga.food_app.services.RoleService;
import com.ganga.food_app.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RoleService roleService;
    private final EmailService emailService;

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
        boolean isDelivery = deliveryResult != null;

        User u = User.builder()
                .name(userForm.getUsername())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .emailToken(UUID.randomUUID())
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

        User savedUser = userService.saveUser(u);
        if(savedUser != null) {
            session.setAttribute("message", new Message("Registration Successfull! An Email Has Sent To Your Inbox.. Click The Link To Verify Your Email", MessageType.WARNING));
            emailService.sendEmail(
                savedUser.getEmail(),
                "Email Verification",
                VerificationHelper.getLinkForEmailVerification(savedUser.getEmailToken())
            );
        } else {
            session.setAttribute("message", new Message("Email not verified ! Token is not associated with user .", MessageType.DANGER));
        }
        return "redirect:/auth/register";
    }

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam(value = "token", required = false) UUID token, HttpSession session) {
        if(token == null) {
            session.setAttribute("message", new Message("Invalid Source! The Page You are Looking To Load Is Invalid", MessageType.WARNING));
            return "verif/email_failed_verification";
        }

        User user = userService.findByToken(token);
        if(user != null) {
            user.setEnabled(1);
            userService.updateUser(user);
            session.setAttribute("message", new Message("Email Verified! Now, You Can Login!", MessageType.SUCCESS));
            return "verif/email_success_verified";
        } else {
            session.setAttribute("message", new Message("Error! Something Went Wrong!", MessageType.DANGER));
            return "verif/email_failed_verification";
        }
    }

}
