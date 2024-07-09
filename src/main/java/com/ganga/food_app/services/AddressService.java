package com.ganga.food_app.services;

import java.util.List;
import java.util.UUID;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.User;

public interface AddressService {
    void saveAddress(Address addr);
    List<Address> getAllAddress();
    List<Address> getUserAddresses(User user);
    Address getAddress(UUID id);
    void removeAddress(UUID id);
}
