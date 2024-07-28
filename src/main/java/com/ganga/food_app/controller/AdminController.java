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

import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminController {
    private final String PATH = "/images/items";

    @Autowired
    private FoodService foodService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

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

        File file = new File(
                "G:\\Coding\\Gangadhar\\Projects\\Java\\Food_Delivery\\food-app\\src\\main\\resources\\static\\images\\items");
        String newName = "food_" + UUID.randomUUID() + ".png";
        Path path = Paths.get(file.getAbsolutePath() + File.separator + newName);

        Files.copy(imgFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        foodService.createFood(Food.builder()
                .name(itemName)
                .description(description)
                .price(price)
                .image("/images/items" + newName)
                .category(category)
                .build());

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
