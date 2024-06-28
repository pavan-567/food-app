package com.ganga.food_app.dao;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganga.food_app.entities.Food;
import com.ganga.food_app.entities.json.FoodKaka;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class FoodDAOImpl {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ResourceLoader resourceLoader;

    @Transactional
    public void loadDataResources() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Resource clasp = resourceLoader.getResource("classpath:static/json/data.json");

            File file = clasp.getFile();

            TypeReference<List<FoodKaka>> foods = new TypeReference<List<FoodKaka>>() {

            };
            List<FoodKaka> foodKakas = mapper.readValue(file, foods);
            for(int i = 0; i < foodKakas.size(); i++) {
                foodKakas.get(i).setImage("/images/items/food_" + (i + 1) + ".png");
                Food f = new Food();
                FoodKaka fk = foodKakas.get(i);
                f.setName(fk.getName());
                f.setImage(fk.getImage());
                f.setPrice(fk.getPrice());
                f.setDescription(fk.getDescription());
                f.setCategory(fk.getCategory());
                entityManager.persist(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
