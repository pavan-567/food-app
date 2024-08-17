package com.ganga.food_app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.helpers.VerificationHelper;
import com.ganga.food_app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    @Autowired
    private UserRepository userRepo;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UUID emailToken = UUID.randomUUID();
        user.setEmailToken(emailToken);
        User savedUser = userRepo.save(user);
        emailService.sendEmail(savedUser.getEmail(), "Verify Account : Ganga's Mart", VerificationHelper.getLinkForEmailVerification(emailToken));
        return savedUser;
    }

    @Override
    public Optional<User> getUserById(UUID uuid) {
        // TODO Auto-generated method stub
        return userRepo.findById(uuid);
    }

    @Override
    public User updateUser(User user) {
        User savedUser = userRepo.save(user);
        return savedUser;
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

    @Override
    public User findByToken(UUID tokenId) {
        return userRepo.findByEmailToken(tokenId).orElse(null);
    }

}
