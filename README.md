# Food Delivery Application

## Overview

This project is a simple food delivery application that allows users to order food items directly from the app. It includes a seamless payment gateway integration via PayPal. The application provides separate interfaces for users, delivery personnel, and administrators. Additionally, the system includes an email verification process for user registration.

## Features

- **User Interface**
    - Browse and search for food items.
    - Add food items to the cart.
    - Place orders with integrated PayPal payment gateway.
    - Track order status.
    - View order history.
    - Manage profile and address information.
    - Email verification during the registration process.

- **Delivery Personnel Interface**
    - View assigned deliveries.
    - Update delivery status (e.g., picked up, on the way, delivered).
    - View delivery history.

- **Admin Interface**
    - Manage food items (add, update, delete).
    - View and manage orders.
    - Assign orders to delivery personnel.
    - View and manage users and delivery personnel.

## Technology Stack

- **Design**: Tailwind CSS, Daisy UI
- **Template Engine** : Thymeleaf
- **Backend**: Spring Boot
- **Database**: MySQL
- **Payment Gateway**: PayPal
- **Authentication**: Spring Security
- **Email Service**: SMTP 
- **Version Control**: Git

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/food-delivery-app.git
   cd food-delivery-app
   ```
   
2. **Install dependencies**:
   ```bash
    cd food-app
    npm install
   ```
3. **Environment Setup**:
   * Create an **env.properties** file in the resources folder and add the below variables shown
   ```bash
    DB_USER=your_database_username
    DB_PASSWORD=your_database_password
    DB_LINK=your_database_link
    
    PAYPAL_CLIENT_ID=your_paypal_client_id
    
    PAYPAL_CLIENT_SECRET=your_paypal_client_secret
    
    PAYPAL_MODE=your_paypal_mode
    
    EMAIL=your_email
    PASSWORD=your_stored_gmail_app_password[Not Real Password]
   ```

4. Run The Application
    * For Tailwind CSS To Work, Run The Command Before Running Spring Boot Application
     ```bash
    npx tailwindcss -i ./src/main/resources/static/css/input.css -o ./src/main/resources/static/css/output.css --watch
     ```
## Usage

### User Interface

1. **Register and Verify Email**:
    - Sign up for an account on the platform.
    - Check your email inbox for a verification link.
    - Click the link to verify your email address.

2. **Browse Food Items and Place an Order**:
    - Explore the available food items through the app.
    - Add desired items to your cart.
    - Proceed to checkout when ready.

3. **Make Payments Securely Through PayPal**:
    - During checkout, choose PayPal as your payment method.
    - Log in to your PayPal account or pay as a guest.
    - Complete the payment securely.

4. **Track Your Order**:
    - After placing your order, view the order status.
    - Track the progress of your order until delivery.

### Delivery Person Interface

1. **Log in to View Assigned Deliveries**:
    - Access the delivery person portal by logging in.
    - View the list of orders assigned to you for delivery.

2. **Update the Status of Deliveries**:
    - Mark orders as "Picked up," "On the way," or "Delivered" as you proceed.
    - Keep users updated with the latest delivery status.

3. **Manage Your Delivery Schedule**:
    - Organize your deliveries based on priority or location.
    - Plan your route to ensure timely deliveries.

### Admin Interface

1. **Log in to Manage the Platform**:
    - Access the admin portal by logging in with your credentials.
    - Monitor and manage all aspects of the platform.

2. **Manage Food Items**:
    - Add new food items to the platform.
    - Update existing items or remove them as needed.
    - Ensure that all information is up-to-date for users.

3. **Oversee Orders and Deliveries**:
    - Track all orders placed by users.
    - Monitor the delivery status and ensure timely completion.

## Design

- **Design**:
    - Built with **Tailwind CSS** and **Daisy UI** for a seamless experience across all devices.

## Contact

For any questions or issues, please contact [Me](mailto:gangathecoder425@gmail.com).