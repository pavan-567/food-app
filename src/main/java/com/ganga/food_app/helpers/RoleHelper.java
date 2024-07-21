package com.ganga.food_app.helpers;

import org.springframework.stereotype.Component;

import com.ganga.food_app.entities.User;

@Component
public class RoleHelper {
    public boolean isAgent(User user) {
        if(user == null) 
            return false;
        for(var role : user.getRoles()) {
            if(role.getRole().equals("ROLE_DELIVERY"))
                return true;
        }
        return false;
    }
}
