package com.ganga.food_app.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ganga.food_app.entities.Food;

public interface FoodRepository extends JpaRepository<Food, UUID> {
    List<Food> findByCategory(String category);

    void deleteById(UUID id);

    @Query("SELECT DISTINCT f.category FROM Food f")
    List<String> findDistinctCategories();
}
