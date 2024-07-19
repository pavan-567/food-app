package com.ganga.food_app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.entities.UserProfile;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.ProfileService;
import com.ganga.food_app.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(Model model) {
        return "profile/profile";
    }

    @PostMapping("/updateDP")
    public String updateProfilePicture(@RequestParam("image") MultipartFile imgFile, Model model, HttpSession session)
            throws IOException {
        try {
            File file = new File("G:\\Coding\\Gangadhar\\Projects\\Java\\Food_Delivery\\food-app\\src\\main\\resources\\static\\images\\profile\\dp");
            User u = (User) model.getAttribute("loggedUser");

            UserProfile up = u.getUserProfile();

            String fileName = u.getName() + "_" + UUID.randomUUID() + ".png";
            Path path = Paths.get(file.getAbsolutePath() + File.separator + fileName);

            Files.copy(imgFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);


            up.setImage("/images/profile/dp/" + fileName);
            profileService.saveProfile(up);
            session.setAttribute("message", new Message("DP Changed Successfully!", MessageType.SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/profile";
    }
}
