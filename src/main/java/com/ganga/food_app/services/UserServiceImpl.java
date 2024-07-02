package com.ganga.food_app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepo;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(UUID uuid) {
        // TODO Auto-generated method stub
        return userRepo.findById(uuid);
    }

    @Override
    public Optional<User> updateUser(User user) {
        // TODO Auto-generated method stub
        User currentUser = userRepo.findById(user.getId()).get();
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        currentUser.setEnabled(user.getEnabled());
        currentUser.setUserProfile(user.getUserProfile());

        User savedUser = userRepo.save(currentUser);
        return Optional.ofNullable(savedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        // TODO Auto-generated method stub
        User user = userRepo.findById(userId).get();
        userRepo.delete(user);
    }

    @Override
    public boolean isUserExist(UUID userId) {
        // TODO Auto-generated method stub
        User u = userRepo.findById(userId).orElse(null);
        return u != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        // TODO Auto-generated method stub
        User u = userRepo.findByEmail(email).orElse(null);
        return u != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        // TODO Auto-generated method stub
        User user = userRepo.findByEmail(email).orElse(null);
        return Optional.ofNullable(user);
    }

}
