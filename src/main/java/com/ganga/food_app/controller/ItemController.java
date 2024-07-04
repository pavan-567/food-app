package com.ganga.food_app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ganga.food_app.entities.Food;
import com.ganga.food_app.helpers.CartInput;
import com.ganga.food_app.services.FoodService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private FoodService foodService;

    @GetMapping("")
    public String foodItems(Model model, @RequestParam(value = "category", required = false) String category) {
        List<Food> foods = null;
        if (category == null)
            foods = foodService.getAllFoods();
        else
            foods = foodService.getFoodViaCategory(category);
        model.addAttribute("foodItems", foods);
        return "food/foodItems";
    }

    @GetMapping("/{id}")
    public String foodItem(@PathVariable UUID id, Model model) {
        Food food = foodService.getFood(id);
        model.addAttribute("foodItem", food);
        return "food/foodItem";
    }

}
