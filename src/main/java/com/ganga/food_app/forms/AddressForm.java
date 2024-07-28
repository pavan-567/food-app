package com.ganga.food_app.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressForm {

    @NotBlank(message = "First Name Required")
    private String firstName;

    @NotBlank(message = "Last Name Required")
    private String lastName;

    @NotBlank(message = "Email Cannot Be Empty")
    @Email(message = "Enter Correct Email")
    private String email;

    @NotBlank(message = "Street Is Required")
    private String street;

    @NotBlank(message = "City Is Required")
    private String city;

    @NotBlank(message = "State Is Required")
    private String state;

    @NotBlank(message = "Zip Code Is Required")
    private String zipCode;

    @NotNull(message = "Country Cannot Be Empty")
    private String country;

    @NotBlank(message = "Phone Number Is Required")
    @Size(min = 8, max = 12, message = "Phone Number Should Be From 8-12 Digits")
    private String phoneNumber;

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

    
}
