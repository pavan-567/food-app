package com.ganga.food_app.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
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

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Cart> cartItems;

    @Column(name = "order_date")
    @CreationTimestamp
    private String orderCreated;

    @Column(name = "order_updated")
    @UpdateTimestamp
    private String orderUpdated;

    public Orders(boolean paymentStatus, int amount, String orderStatus) {
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }

    public Orders() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(String orderCreated) {
        this.orderCreated = orderCreated;
    }

    public String getOrderUpdated() {
        return orderUpdated;
    }

    public void setOrderUpdated(String orderUpdated) {
        this.orderUpdated = orderUpdated;
    }


    public List<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.cartItems = cartItems;
    }
    

    // Helper Methods
    public void addToCart(Cart cart) {
        if(cartItems == null)
            cartItems = new ArrayList<>();
        cartItems.add(cart);
    }

    @Override
    public String toString() {
        return "Orders [id=" + id + ", paymentStatus=" + paymentStatus + ", amount=" + amount + ", orderStatus="
                + orderStatus + "]";
    }

 

}
