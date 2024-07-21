package com.ganga.food_app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ganga.food_app.entities.Role;


public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("SELECT r FROM Role r WHERE r.role = 'ROLE_USER'")
    Role getUserRole();

    @Query("SELECT r FROM Role r WHERE r.role = 'ROLE_ADMIN'")
    Role getAdminRole();

    @Query("SELECT r FROM Role r WHERE r.role = 'ROLE_DELIVERY'")
    Role getDeliveryRole();
}
