package com.ganga.food_app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganga.food_app.entities.Food;
import com.ganga.food_app.repositories.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepo;

    @Override
    public List<Food> getAllFoods() {
        return foodRepo.findAll();
    }

    @Override
    public Food getFood(UUID foodId) {
        Optional<Food> food = foodRepo.findById(foodId);
        if(food.isPresent())
            return food.get();
        return null;
    }

    @Override
    public List<Food> getFoodViaCategory(String category) {
        // TODO Auto-generated method stub
        return foodRepo.findByCategory(category);
    }

}
