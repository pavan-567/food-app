package com.ganga.food_app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ganga.food_app.entities.Role;
import com.ganga.food_app.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UserDAOImpl {
    private EntityManager entityManager;


    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addUser() {

        User u = new User("Gangadhar", "Ganga@gmail.com", "UdemyGanga");
        Role r = new Role("ROLE_USER");
        u.addRole(r);
        entityManager.persist(u);
    }

    
}
