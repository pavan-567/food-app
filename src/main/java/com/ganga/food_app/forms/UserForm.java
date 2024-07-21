package com.ganga.food_app.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {
    @NotBlank(message = "Username is Required")
    @Size(min = 3, message = "Min 3 Characters are Required")
    private String username;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password Is Required")
    @Size(min = 6, message = "Min 6 Characters are Required")
    private String password;

    @NotBlank(message = "Passwords Not Matched")
    @Size(min = 6, message = "Min 6 Characters Required")
    private String confirmPassword;

    @NotBlank(message = "First Name Required")
    @Size(min = 3, message = "Min 3 Characters are Required")
    private String firstName;
    
    @NotBlank(message = "Last Name Required")
    @Size(min = 3, message = "Min 3 Characters are Required")
    private String lastName;
    
    @NotBlank(message = "Phone Number Required")
    @Size(min = 8, max=12, message = "Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Gender Required")
    private String gender;

    @NotBlank(message = "State Required")
    private String state;

    @NotBlank(message = "City Required")
    private String city;

    @NotBlank(message = "Country Required")
    private String country;

    public UserForm(String username, String email, String gender, String password, String firstName, String lastName,
            String phoneNumber, String state, String city, String country) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.city = city;
        this.country = country;
    }

    public UserForm() {}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void checkPassword() {
        if(this.password == null || this.confirmPassword == null)
            return;
        else if(!this.password.equals(confirmPassword)) {
            this.confirmPassword = null;
        }
    }

    @Override
    public String toString() {
        return "UserForm [username=" + username + ", email=" + email + ", password=" + password + ", firstName="
                + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", gender=" + gender + "]";
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
}
