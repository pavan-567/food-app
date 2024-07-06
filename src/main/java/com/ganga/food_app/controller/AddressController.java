package com.ganga.food_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.services.AddressService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public String addressForm(Model model) {
        model.addAttribute("address", new Address());
        return "addr/addrForm";
    }

    @PostMapping("/create")
    public String createAddress(@Valid @ModelAttribute("address") Address address, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldErrors());
            return "addr/addrForm";
        }

        // Get Authenticated User
        User currUser = (User) model.getAttribute("loggedUser");
        address.setUser(currUser);
        addressService.saveAddress(address);
        return "redirect:/orders";
    }
}
