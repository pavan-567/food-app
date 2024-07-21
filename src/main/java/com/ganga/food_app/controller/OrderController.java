package com.ganga.food_app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.services.AddressService;
import com.ganga.food_app.services.OrdersService;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public String orders(Model model) {
        User currUser = (User) model.getAttribute("loggedUser");
        List<Orders> orders = ordersService.getOrders(currUser);
        List<String> orderNames = orders.stream()
        .map(order -> order.getCartItems().stream()
          .map(item -> item.getFood().getName() + " x " + item.getQuantity() + " ")
          .collect(Collectors.joining(", ")))
        .collect(Collectors.toList());
        

        model.addAttribute("orders", orders);
        model.addAttribute("orderNames", orderNames);
        return "orders/orders";
    }

    @GetMapping("/{id}")
    public String order(@PathVariable("id") UUID orderId, Model model) {
        Orders order = ordersService.getOrder(orderId);
        model.addAttribute("order", order);
        return "orders/order";
    }

    @GetMapping("/placeOrder")
    public String order(Model model) {
        User user = (User) model.getAttribute("loggedUser");
        List<Address> addresses = addressService.getUserAddresses(user);
        model.addAttribute("address", addresses);
        return "orders/placeOrder";
    }

    @PostMapping("/placeOrder")
    public String placeOrder() {
        return "redirect:/orders";
    }
}
