package com.ganga.food_app.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.User;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByUser(User user);
    Optional<Address> findById(UUID id);
    void deleteById(UUID id);
}
