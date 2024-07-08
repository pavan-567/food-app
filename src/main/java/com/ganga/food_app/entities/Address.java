package com.ganga.food_app.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "address_id")
    @JdbcType(VarcharJdbcType.class)
    private UUID id;

    @NotBlank(message = "First Name Required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last Name Required")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email Cannot Be Empty")
    @Email(message = "Enter Correct Email")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Street Is Required")
    @Column(name = "street")
    private String street;

    @NotBlank(message = "City Is Required")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "State Is Required")
    @Column(name = "state")
    private String state;

    @NotBlank(message = "Zip Code Is Required")
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull(message = "Country Cannot Be Empty")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "Phone Number Is Required")
    @Size(min = 8, max = 12, message = "Phone Number Should Be From 8-12 Digits")
    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "address")
    private List<Orders> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String firstName, String lastName, String email, String street, String city, String state,
            String zipCode, String country, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public Address() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Helper Methods
    public void addOrder(Orders order) {
        if (orders == null)
            orders = new ArrayList<>();
        orders.add(order);
        order.setAddress(this);
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", street=" + street + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", country="
                + country + ", phoneNumber=" + phoneNumber + "]";
    }

}
