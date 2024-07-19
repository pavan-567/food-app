package com.ganga.food_app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.AddressService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public String addresses(Model model) {
        User user = (User) model.getAttribute("loggedUser");
        List<Address> addresses = addressService.getUserAddresses(user);
        model.addAttribute("addresses", addresses);
        return "addr/address";
    }

    @GetMapping("/create")
    public String addressForm(Model model) {
        model.addAttribute("address", new Address());
        return "addr/addrForm";
    }

    @PostMapping("/create")
    public String createAddress(@Valid @ModelAttribute("address") Address address, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "addr/addrForm";
        }

        // Get Authenticated User
        User currUser = (User) model.getAttribute("loggedUser");
        address.setUser(currUser);
        addressService.saveAddress(address);


        session.setAttribute("message", new Message("Address Created Successfully!", MessageType.SUCCESS));

        return "redirect:/address";
    }

    @PostMapping("/delete")
    public String deleteAddress(@RequestParam("addressId") UUID id, HttpSession session) {
        if(addressService.isAddressInUse(id) != null) {
            session.setAttribute("message", new Message("This address is in use with atleast one of your Orders.. It Cannot Be Deleted..", MessageType.DANGER));
        } else {
            addressService.removeAddress(id);
            session.setAttribute("message", new Message("Address Deleted Successfully", MessageType.SUCCESS));
        }
        return "redirect:/address";
    }
}
