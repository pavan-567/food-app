package com.ganga.food_app.services;

import java.util.List;

import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;

public interface OrdersService {
    void save(Orders order);
    List<Orders> getOrders(User user);
}
