package com.ganga.food_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganga.food_app.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    
}