package com.ganga.food_app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganga.food_app.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders, UUID> {

}
