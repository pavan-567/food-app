package com.ganga.food_app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.repositories.OrderRepository;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void save(Orders order) {
        orderRepository.save(order);
    }

    @Override
    public List<Orders> getOrders(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Orders> getAllOrders() {
        // TODO Auto-generated method stub
        return orderRepository.findAll();
    }

    @Override
    public Orders getOrder(UUID id) {
        Optional<Orders> order = orderRepository.findById(id);
        if (order.isPresent())
            return order.get();
        return null;
    }

}
