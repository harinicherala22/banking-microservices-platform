# 🏦 Banking Microservices Platform

A scalable backend banking application built using **Java, Spring Boot, Microservices Architecture, JWT Security, Redis Cache, Kafka Messaging, Docker, and MySQL**.

This project demonstrates enterprise backend development concepts including authentication, API Gateway routing, distributed services, caching, asynchronous messaging, and transactional fund transfer.


## 🚀 Architecture

Client / Postman
       ↓
API Gateway (8080)
       ↓
 ┌───────────────┬────────────────┐
 ↓                                ↓
Auth Service (8081)         Account Service (8082)
JWT Login/Register          Accounts / Deposit / Withdraw / Transfer

Infrastructure:
MySQL | Redis | Kafka | Docker

🧩 Microservices
🔐 Auth Service

Handles:

User Registration
User Login
JWT Token Generation
API Security
💰 Account Service

Handles:
Create Account
Get Balance
Deposit Amount
Withdraw Amount
Fund Transfer
Redis Caching

🌐 API Gateway

Handles:

Central Routing
Entry point for clients
Service forwarding
⚙️ Tech Stack
Java 17
Spring Boot
Spring Security
JWT
Spring Cloud Gateway
Spring Data JPA
MySQL
Redis
Apache Kafka
Docker
Maven

🔥 Features

Secure JWT Authentication
Microservices-based Architecture
Account Management APIs
Deposit / Withdraw Operations
Atomic Fund Transfer using @Transactional
Redis Cache for Faster Reads
Kafka Event Messaging
Dockerized Services
📌 Sample APIs
Register User
POST /auth/register
Login
POST /auth/login
Create Account
POST /account/create
Deposit
PUT /account/deposit/{email}/{amount}
Withdraw
PUT /account/withdraw/{email}/{amount}
Transfer Money
POST /account/transfer

Body:

{
  "fromEmail": "harini@gmail.com",
  "toEmail": "user2@gmail.com",
  "amount": 2000
}

🐳 Run with Docker
docker compose up --build

📈 Future Enhancements
Transaction History Service
Notifications via Kafka Consumers
Role-based Admin Features
CI/CD with GitHub Actions / Jenkins
AWS Deployment
Monitoring with Prometheus + Grafana

**👩‍💻 Author**

Harini Cherala

GitHub: https://github.com/harinicherala22
