package com.ganga.food_app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ganga.food_app.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(UUID uuid);
    Optional<User> updateUser(User user);
    void deleteUser(UUID userId);
    boolean isUserExist(UUID userId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
}
