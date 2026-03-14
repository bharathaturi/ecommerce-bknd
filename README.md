# E-Commerce Application

## Description
A comprehensive full-stack e-commerce web application that allows users to seamlessly browse products, manage their shopping carts, and place orders. The platform provides a secure and intuitive interface for user registration, product discovery, wishlist management, and secure checkout processing.

## Live Demo
- **Frontend App:** [https://ecommerce-ft.vercel.app]
- **Backend API Server:** [https://ecommerce-bknd.onrender.com]

## Features
- Secure User Authentication and Authorization (JWT-based)
- Intuitive Product Catalog and Search
- Real-time Shopping Cart and Wishlist Management
- Secure Checkout Process and Order Placement
- User Profile and Order History Management
- Responsive Design for Web and Mobile Devices

## Tech Stack

### Frontend
- **React:** UI library
- **Redux Toolkit:** State management (`@reduxjs/toolkit`, `react-redux`)
- **Tailwind CSS:** Utility-first CSS framework for styling
- **React Router:** Client-side routing (`react-router-dom`)
- **Axios:** API requests
- **Vite:** Next Generation Frontend Tooling

### Backend
- **Java:** Version 17
- **Spring Boot:** Framework for building the RESTful API (Version 3.2.0)
  - **Spring Data JPA:** For database mapping and interactions
  - **Spring Security:** For securing API endpoints and managing authentication (JWT)
  - **Spring Web:** For building web applications and RESTful APIs
- **PostgreSQL:** Primary relational database
- **Maven:** Build automation and dependency management
- **Lombok:** Java library to reduce boilerplate code

## Installation

### Prerequisites
- Node.js and npm
- Java SDK (JDK 17 or higher recommended)
- Maven
- PostgreSQL

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd ecommerce-backend
Build and run the Spring Boot application:
```bash
mvn clean install
mvn spring-boot:run
