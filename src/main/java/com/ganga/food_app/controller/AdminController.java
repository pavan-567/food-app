package com.ganga.food_app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ganga.food_app.services.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ganga.food_app.entities.Food;
import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.repositories.RoleRepository;
import com.ganga.food_app.services.FoodService;
import com.ganga.food_app.services.OrdersService;
import com.ganga.food_app.services.UserService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final FileStorageService fileStorageService;
    private final FoodService foodService;
    private final OrdersService ordersService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping
    public String home() {
        return "/admin/home";
    }

    @GetMapping("/list")
    public String foodItemList(Model model) {
        List<Food> foods = foodService.getAllFoods();
        model.addAttribute("foods", foods);
        return "/admin/foods";
    }

    @PostMapping("/removeItem")
    public String removeFoodItem(@RequestParam("foodId") UUID id) {
        foodService.deleteFood(id);
        return "redirect:/admin/list";
    }

    @GetMapping("/add")
    public String addFood(Model model) {
        List<String> categories = foodService.getFoodCategories();
        model.addAttribute("categories", categories);
        return "/admin/createFood";
    }

    @PostMapping("/add")
    public String processFood(@RequestParam("image") MultipartFile imgFile, @RequestParam("name") String itemName,
            @RequestParam("description") String description, @RequestParam("category") String category,
            @RequestParam("price") int price) throws IOException {

        Food food = foodService.createFood(itemName, description, category, price);
        String path = fileStorageService.saveFile(imgFile, food);
        food.setImage(path);
        foodService.saveFood(food);
        return "redirect:/admin/list";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        List<Orders> allOrders = ordersService.getAllOrders();
        List<String> orderNames = allOrders.stream()
                .map(order -> order.getCartItems().stream()
                        .map(item -> item.getFood().getName() + " x " + item.getQuantity() + " ")
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.toList());

        List<User> agents = userService.getAllUsers().stream()
                .filter(user -> user.getRoles().contains(roleRepository.getDeliveryRole())).toList();
        model.addAttribute("orders", allOrders);
        model.addAttribute("orderNames", orderNames);
        model.addAttribute("deliveryAgents", agents);
        return "/admin/orders";
    }

    @PostMapping("/status")
    public String modifyStatus(@RequestParam("orderId") UUID id, @RequestParam("status") String status) {
        Orders order = ordersService.getOrder(id);
        order.setOrderStatus(status);
        ordersService.save(order);
        return "redirect:/admin/orders";
    }
}
