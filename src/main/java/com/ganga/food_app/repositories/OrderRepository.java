package com.ganga.food_app.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findByUser(User user);
    Optional<Orders> findById(UUID id);
}
