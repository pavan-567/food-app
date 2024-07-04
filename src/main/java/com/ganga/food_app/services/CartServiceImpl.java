package com.ganga.food_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganga.food_app.entities.Cart;
import com.ganga.food_app.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Override
    @Transactional
    public void save(Cart cartItem) {
        cartRepo.save(cartItem);
    }

}
