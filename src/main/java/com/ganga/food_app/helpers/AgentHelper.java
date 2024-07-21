package com.ganga.food_app.helpers;

import org.springframework.stereotype.Component;

import com.ganga.food_app.entities.Orders;
import com.ganga.food_app.entities.User;

@Component
public class AgentHelper {

    public boolean hasAgentAssigned(User agent, Orders order) {
        if(order.getDeliveryAgent() == null)
            return false;
        if (order.getDeliveryAgent().getId().equals(agent.getId()))
            return true;
        return false;
    }


    public boolean isAgentLocation(User agent, Orders order) {
        if(order.getAddress().getCity().toLowerCase().equals(agent.getUserProfile().getCity().toLowerCase())){
            return true;
        }
        return false;
    }
}
