package com.ganga.food_app.controller;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.FileStorageService;
import com.ganga.food_app.services.ProfileService;
import com.ganga.food_app.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final FileStorageService storageService;
    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping
    public String profile(Model model) {
        return "profile/profile";
    }

    @PostMapping("/updateDP")
    public String updateProfilePicture(@RequestParam("image") MultipartFile imgFile, Model model, HttpSession session)
            throws IOException {
        User user = (User) model.getAttribute("loggedUser");
        final String filePath = storageService.saveFile(imgFile, user);

        if (filePath == null) {
            session.setAttribute("message", new Message("There Was an Error Uploading DP!", MessageType.DANGER));
        } else {
            profileService.uploadDP(filePath, user);
            session.setAttribute("message", new Message("DP Changed Successfully!", MessageType.SUCCESS));
        }

        return "redirect:/profile";
    }
}
