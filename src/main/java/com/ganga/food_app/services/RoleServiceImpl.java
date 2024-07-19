package com.ganga.food_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganga.food_app.entities.Role;
import com.ganga.food_app.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.getUserRole();
    }

    @Override
    public Role getAdminRole() {
        // TODO Auto-generated method stub
        return roleRepository.getAdminRole();
    }

}
