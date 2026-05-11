# Online Store Application

A full-featured e-commerce web application built with Java, Spring Boot, Thymeleaf, Bootstrap, and MySQL. The application allows users to browse products, register and log in, add items to a shopping cart, and complete payments using Flutterwave.

---

## User Features
- User registration and login
- Secure authentication with Spring Security
- Browse all products
- Search products by keyword or category
- View detailed product information
- Add products to cart
- Update cart quantity
- Remove items from cart
- Checkout and payment integration
- Order summary

### Admin Features
- Add new products
- Edit existing products
- Delete products
- Manage product categories

### Payment Integration
- Flutterwave payment gateway

## Technologies Used

### Backend
- Java 17+
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate

### Frontend
- Thymeleaf
- Bootstrap 5
- Bootstrap Icons

### Database
- MySQL

### Tools
- Maven
- GitHub
- Postman

### Payment Providers
- Flutterwave

## Getting Started

### Clone the project

## 1. Clone the Repository

```bash
git clone https://github.com/your-username/online-store.git
cd online-store

````

## 2. DPDATE YOUR application.yml

```bash

spring:
  application:
    name: online-store
  mvc:
    hiddenmethod:
      filter:
        enabled: true

  datasource:
    url: 
    username: 
    password: 

  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true

flutterwave:
  public-key: ${publicKey}
  secret-key: ${secretKey}
  base-url: ${baseUrl}
```
### 3. UPDATE YOUR application.yml