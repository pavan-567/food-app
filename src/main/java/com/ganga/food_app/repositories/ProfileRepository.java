package com.ganga.food_app.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganga.food_app.entities.User;
import com.ganga.food_app.entities.UserProfile;

public interface ProfileRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findByUser(User user);
}
