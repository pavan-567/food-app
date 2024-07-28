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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    // Helper Methods
    public void addOrder(Orders order) {
        if (orders == null)
            orders = new ArrayList<>();
        orders.add(order);
        order.setAddress(this);
    }

}
