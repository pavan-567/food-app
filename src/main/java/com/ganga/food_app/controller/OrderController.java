package com.ganga.food_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.services.AddressService;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public String placeOrder() {
        return "redirect:/items";
    }

    @GetMapping("/placeOrder")
    public String order(Model model) {
        User user = (User) model.getAttribute("loggedUser");
        List<Address> addresses = addressService.getUserAddresses(user);
        model.addAttribute("address", addresses);
        return "orders/order";
    }
}
