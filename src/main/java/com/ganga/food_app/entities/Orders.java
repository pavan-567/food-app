package com.ganga.food_app.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "order_id")
    @JdbcType(VarcharJdbcType.class)
    private UUID id;

    @Column(name = "payment_status")
    private boolean paymentStatus;

    @Column(name = "amount")
    private int amount;

    @Column(name = "order_status")
    private String orderStatus;
    
    @Column(name = "payment_id")
    private String paymentID;

    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Cart> cartItems;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_agent_id")
    private User deliveryAgent;


    @Column(name = "order_date")
    @CreationTimestamp
    private String orderCreated;

    @Column(name = "order_updated")
    @UpdateTimestamp
    private String orderUpdated;

    // Helper Methods
    public void addToCart(Cart cart) {
        if(cartItems == null)
            cartItems = new ArrayList<>();
        cartItems.add(cart);
    }
}
