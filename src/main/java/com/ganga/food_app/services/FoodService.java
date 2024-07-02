package com.ganga.food_app.services;

import java.util.List;
import java.util.UUID;

import com.ganga.food_app.entities.Food;

public interface FoodService {
    List<Food> getAllFoods();
    List<Food> getFoodViaCategory(String category);
    Food getFood(UUID foodId);
}
