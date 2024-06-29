package com.ganga.food_app.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    public UserForm(String username, String email, String gender, String password, String firstName, String lastName,
            String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public UserForm() {}

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
    
}
