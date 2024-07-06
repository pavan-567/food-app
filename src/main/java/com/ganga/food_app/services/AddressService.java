package com.ganga.food_app.services;

import java.util.List;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.User;

public interface AddressService {
    void saveAddress(Address addr);
    List<Address> getAllAddress();
    List<Address> getUserAddresses(User user);
}
