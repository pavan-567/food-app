package com.ganga.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ganga.food_app.dao.FoodDAOImpl;
import com.ganga.food_app.dao.UserDAOImpl;

@Controller
public class DemoController {
    @Autowired
    private UserDAOImpl userDao;

    @Autowired
    private FoodDAOImpl foodDao;

    @GetMapping("/")
    public String demoTest() {
        return "demo";
    }

    @GetMapping("/loadResources")
    public String loadResources() {
        return "demo";
    }
}
