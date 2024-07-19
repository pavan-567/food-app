package com.ganga.food_app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ganga.food_app.entities.Food;
import com.ganga.food_app.helpers.CartInput;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.FoodService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/add-to-cart")
    public String addCart(@RequestParam("foodId") UUID id, HttpSession session, HttpServletRequest request) {

        Food food = foodService.getFood(id);
        List<CartInput> cart = (List<CartInput>) session.getAttribute("cart");
        if (cart == null)
            cart = new ArrayList<>();

        boolean isExist = false;
        for (var item : cart) {
            if (item.getFood().getId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                item.setTotalAmount((item.getTotalAmount() * item.getQuantity()));
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            CartInput item = new CartInput();
            item.setQuantity(1);
            item.setTotalAmount(food.getPrice());
            item.setFood(food);
            cart.add(item);
        }
        session.setAttribute("cart", cart);

        String referrer = request.getHeader("Referer");
        return "redirect:" + (referrer != null ? referrer : "/");
    }

    @GetMapping("/cart")
    public String Cart(Model m, HttpSession session) {
        return "food/cart";
    }

    @PostMapping("/quantity")
    public String Quantity(@RequestParam("foodId") UUID id, @RequestParam("opt") String operation,
            HttpSession session, HttpServletRequest request) {
        List<CartInput> cart = (List<CartInput>) session.getAttribute("cart");
        if(cart != null) {
            for (var item : cart) {
                if (item.getFood().getId().equals(id)) {
                    if (operation.equals("incr")) {
                        item.setQuantity(item.getQuantity() + 1);
                        item.setTotalAmount(item.getFood().getPrice() * item.getQuantity());
                    } else {
                        if (item.getQuantity() - 1 == 0) {
                            cart.remove(item);
                        } else {
                            item.setQuantity(item.getQuantity() - 1);
                            item.setTotalAmount(item.getFood().getPrice() * item.getQuantity());
                        }
                    }
                    break;
                }
            }
        }
        // Referer (sometimes spelled Referrer) is an HTTP header sent by the browser. It contains the URL of the web page that linked to the resource being requested. In simpler terms, it tells the server the URL from which the request originated.
        String referrer = request.getHeader("Referer");
        return "redirect:" + (referrer != null ? referrer : "/");
    }

    @PostMapping("/removeItem")
    public String deleteCartItem(@RequestParam("foodId") UUID id, HttpSession session) {
        List<CartInput> cartItems = (List<CartInput>) session.getAttribute("cart");
        for (var item : cartItems) {
            if (item.getFood().getId().equals(id)) {
                cartItems.remove(item);
                break;
            }
        }
        return "redirect:/cart";
    }

}
