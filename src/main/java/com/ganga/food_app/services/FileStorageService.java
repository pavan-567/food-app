package com.ganga.food_app.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ganga.food_app.entities.Food;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ganga.food_app.entities.User;

@Service
public class FileStorageService {
    @Value("${application.file.upload.photos-output-path}")
    private String fileUploadPath;

    public String saveFile(MultipartFile sourceFile, User user) {
        final String fileUploadSubPath = "users" + File.separator + user.getName().toLowerCase();
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    public String saveFile(MultipartFile sourceFile, Food food){
        final String fileUploadSubPath = "foods" + File.separator + food.getCategory().toLowerCase();
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(MultipartFile sourceFile, String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                System.out.println("Failed To Create Target Folder!");
                return null;
            }
        }

        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + File.separator + System.currentTimeMillis() + "." + fileExtension;

        Path targetPath = Paths.get(targetFilePath);

        try {
            Files.write(targetPath, sourceFile.getBytes());
            return targetFilePath.substring(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf(".");

        if (lastDotIndex == -1)
            return "";

        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }

}
