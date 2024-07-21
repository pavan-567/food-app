package com.ganga.food_app.services;

import java.util.List;
import java.util.UUID;

import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.helpers.CartInput;
import com.paypal.api.payments.Payment;

public interface OrdersService {
    void save(Orders order);
    List<Orders> getOrders(User user);
    List<Orders> getAllOrders();
    Orders getOrder(UUID id);
    void placeOrder(Payment payment, UUID addressId, User user, List<CartInput> cartInputs, String paymentId);
    void placeOrder(UUID addressId, User user, List<CartInput> cartInputs, int totalAmount);
    List<Orders> getAgentOrders(User agent);
}
