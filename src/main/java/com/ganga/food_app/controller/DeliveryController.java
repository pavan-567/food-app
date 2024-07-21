package com.ganga.food_app.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.helpers.Message;
import com.ganga.food_app.helpers.HelperEnums.MessageType;
import com.ganga.food_app.services.OrdersService;
import com.ganga.food_app.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String home() {
        return "agent/home";
    }


    @GetMapping("/orders")
    public String getAgentOrders(Model model) {
        User agent = (User) model.getAttribute("loggedUser");
        List<Orders> agentDeliveryOrders = ordersService.getAgentOrders(agent);

        List<String> orderNames = agentDeliveryOrders.stream()
                .map(order -> order.getCartItems().stream()
                        .map(item -> item.getFood().getName() + " x " + item.getQuantity() + " ")
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.toList());
        model.addAttribute("orderNames", orderNames);
        model.addAttribute("agentOrders", agentDeliveryOrders);
        return "agent/orders";
    }

    @GetMapping("/orders/{id}")
    public String getAgentOrder(@PathVariable("id") UUID orderId, Model model) {
        Orders agentOrder = ordersService.getOrder(orderId);

        User agent = (User) model.getAttribute("loggedUser");

        if(agentOrder.getDeliveryAgent().getId().equals(agent.getId())) {
            model.addAttribute("order", agentOrder);
            return "agent/order";
        } else {
            return "redirect:/delivery/orders";
        }
    }

    @PostMapping("/assignAgent")
    public String getOrder(@RequestParam("agent") UUID agentID, @RequestParam("order") UUID orderID,
            HttpSession session) {
        Orders order = ordersService.getOrder(orderID);
        User agent = userService.getUserById(agentID).get();
        order.setDeliveryAgent(agent);

        ordersService.save(order);

        session.setAttribute("message", new Message(
                "Agent Successfully Added To Order - " + orderID.toString().toUpperCase(), MessageType.SUCCESS));

        return "redirect:/admin/orders";
    }

    @PostMapping("/status")
    public String modifyStatus(@RequestParam("orderId") UUID id, @RequestParam("status") String status) {
        Orders order = ordersService.getOrder(id);
        order.setOrderStatus(status);
        ordersService.save(order);
        return "redirect:/delivery/orders/" + id.toString();
    }
}
