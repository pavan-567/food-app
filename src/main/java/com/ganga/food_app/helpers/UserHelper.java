package com.ganga.food_app.helpers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.ganga.food_app.entities.User;

@Component
public class UserHelper {
    public boolean isAdmin(User currentUser) {
        for (var role : currentUser.getRoles()) {
            if (role.getRole().equals("ROLE_ADMIN"))
                return true;
        }
        return false;
    }
}
