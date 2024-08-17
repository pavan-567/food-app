package com.ganga.food_app.helpers;

import java.util.UUID;

public class VerificationHelper {

    public static String getLinkForEmailVerification(UUID emailToken){
        String link = "http://localhost:8080/auth/verify-email?token=" + emailToken.toString();
        return link;
    }
}
