# ByteBites - Enterprise Cloud Food Delivery Platform (Backend Services)

## Student Information
* **Student Name:** U. Anjana Heshan
* **Student ID:** [Your IJSE Student ID Here]
* **Module:** ITS 2130 - Enterprise Cloud Architecture (ECA)
* **GCP Project ID:** [Your GCP Project ID Here]

## Project Description
This repository contains the core business logic microservices of the ByteBites Enterprise Food Delivery Application. It includes menu management, order placement, order dispatch and delivery tracking, and payment processing.

## Tech Stack
* **Language:** Java 25
* **Framework:** Spring Boot, Spring Cloud, Spring Data JPA
* **Databases:**
  * **Menu Service (Port 8081):** MongoDB (Non-Relational / NoSQL)
  * **Order Service (Port 8082):** MySQL (Relational)
  * **Delivery Service (Port 8083):** PostgreSQL (Relational)
  * **Payment Service (Port 8084):** PostgreSQL (Relational)

## Local Getting Started Instructions
To run these services locally, follow these steps:

### Prerequisites
1. Make sure you have JDK 25 and Maven installed on your system.
2. Make sure you have Docker Desktop installed and running.

### 1. Start the Databases
Run the Docker Compose command in the root project directory to spin up MongoDB, MySQL, and PostgreSQL containers:
```bash
docker compose up -d
```

### 2. Verify Platform Services are Running
Ensure that the **Config Server**, **Eureka Server**, and **API Gateway** from the `food-delivery-backend-platform` repository are running.

### 3. Run Microservices
Run the following commands in their respective directories to start the microservices:

* **Menu Service:**
  ```bash
  cd menu-service
  ./mvnw spring-boot:run
  ```
* **Order Service:**
  ```bash
  cd ../order-service
  ./mvnw spring-boot:run
  ```
* **Delivery Service:**
  ```bash
  cd ../delivery-service
  ./mvnw spring-boot:run
  ```
* **Payment Service:**
  ```bash
  cd ../payment-service
  ./mvnw spring-boot:run
  ```
