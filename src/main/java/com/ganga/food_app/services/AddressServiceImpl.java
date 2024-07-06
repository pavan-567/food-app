package com.ganga.food_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganga.food_app.entities.Address;
import com.ganga.food_app.entities.User;
import com.ganga.food_app.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void saveAddress(Address addr) {
        addressRepository.save(addr);
    }

    @Override
    public List<Address> getAllAddress() {
       return addressRepository.findAll();
    }

    @Override
    public List<Address> getUserAddresses(User user) {
        List<Address> userAddr = addressRepository.findByUser(user);
        return userAddr;
    }



}
