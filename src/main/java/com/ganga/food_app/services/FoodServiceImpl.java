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
        return food.orElse(null);
    }

    @Override
    public List<Food> getFoodViaCategory(String category) {
        return foodRepo.findByCategory(category);
    }

    @Override
    public void deleteFood(UUID foodId) {
        foodRepo.deleteById(foodId);
    }

    @Override
    public List<String> getFoodCategories() {
        return foodRepo.findDistinctCategories();
    }

    @Override
    public void saveFood(Food food) {
       foodRepo.save(food);
    }

    @Override
    public Food createFood(String name, String description, String category, Integer price) {
        return Food.builder()
                .name(name)
                .description(description)
                .category(category)
                .price(price)
                .image(null)
                .build();
    }

}
