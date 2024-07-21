package com.ganga.food_app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.Cart;
import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.helpers.CartInput;
import com.ganga.food_app.repositories.OrderRepository;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressService addressService;

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

    @Override
    public void placeOrder(Payment payment, UUID addressId, User user, List<CartInput> cartInputs, String paymentId) {
                Address addr = addressService.getAddress(addressId);
                Orders order = new Orders();

                for(var inp: cartInputs) {
                    Cart c = new Cart();
                    c.setQuantity(inp.getQuantity());
                    c.setFood(inp.getFood());
                    order.addToCart(c);
                }

                order.setPaymentStatus(true);
                order.setAddress(addr);
                order.setUser(user);
                order.setPaymentID(paymentId);
                order.setPaymentMethod("card");


                List<Transaction> t = payment.getTransactions();
                double total = 0;
                for(var transaction: t) {
                    total = Double.parseDouble(transaction.getAmount().getTotal());
                }
                order.setAmount((int) Math.round(total));
                order.setOrderStatus("processing");

                orderRepository.save(order);
                
    }

    @Override
    public void placeOrder(UUID addressId, User user, List<CartInput> cartInputs, int totalAmount) {
        Address addr = addressService.getAddress(addressId);
        Orders order = new Orders();
        for(var inp: cartInputs) {
            Cart c = new Cart();
            c.setQuantity(inp.getQuantity());
            c.setFood(inp.getFood());
            order.addToCart(c);
        }

        order.setPaymentStatus(false);
        order.setAddress(addr);
        order.setUser(user);
        order.setPaymentMethod("cash");
        order.setAmount(totalAmount);
        order.setOrderStatus("processing");

        orderRepository.save(order);
    }

    @Override
    public List<Orders> getAgentOrders(User agent) {
       return orderRepository.findByDeliveryAgent(agent);
    }

}
