package com.ganga.food_app.helpers;


import com.ganga.food_app.entities.Food;

public class CartInput {
    private int quantity;
    private int totalAmount;
    private Food food;

    public CartInput() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    

    @Override
    public String toString() {
        return "CartInput [foodId=" + food.getId() + ", quantity=" + quantity + ", totalAmount=" + totalAmount + "]";
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    

}
