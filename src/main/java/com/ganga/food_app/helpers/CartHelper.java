package com.ganga.food_app.helpers;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ganga.food_app.entities.Food;

import jakarta.servlet.http.HttpSession;

@Component
public class CartHelper {
    public boolean isItemInCart(Food currFood) {
        List<CartInput> cartInputs = null;
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                    .getSession();

            cartInputs = (List<CartInput>) session.getAttribute("cart");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cartInputs == null)
            return false;
        for (CartInput cart : cartInputs) {
            if (cart.getFood().getId().equals(currFood.getId())) {
                return true;
            }
        }
        return false;
    }

    public int getItemQuantity(Food food) {
        List<CartInput> cartInputs = null;

        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
            .getSession();
            cartInputs = (List<CartInput>) session.getAttribute("cart");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (CartInput cart : cartInputs) {
            if (cart.getFood().getId().equals(food.getId())) {
                return cart.getQuantity();
            }
        }
        return 0;
    }


    public int getTotalPrice() {
        List<CartInput> cartInputs = null;
        try {
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
            .getSession();
            cartInputs = (List<CartInput>) session.getAttribute("cart");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int total = 0;
        if(cartInputs != null) {
            for(var item: cartInputs) {
                total += item.getTotalAmount();
            }
        }
        return total;
    }
}
