package com.ganga.food_app.services;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.entities.UserProfile;

public interface ProfileService {
    UserProfile getUserProfile(User user);
    void saveProfile(UserProfile profile);
}
