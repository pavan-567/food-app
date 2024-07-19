package com.ganga.food_app.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.User;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByUser(User user);
    Optional<Address> findById(UUID id);

    @Query("SELECT addr FROM Address addr JOIN FETCH Orders o ON o.address = addr WHERE addr.id= ?1")
    Address isAddressUsed(UUID addressId);
}
