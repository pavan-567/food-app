package com.ganga.food_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.entities.UserProfile;
import com.ganga.food_app.repositories.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserProfile getUserProfile(User user) {
        // TODO Auto-generated method stub
        Optional<UserProfile> up = profileRepository.findByUser(user);
        if(up.isPresent())
            return up.get();
        return null;
    }

}
