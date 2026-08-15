# ByteBites - Enterprise Cloud Food Delivery Platform (Backend Services)

## Student Information
* **Student Name:** Anjana Heshan
* **Student ID:** 241722056
* **Module:** ITS 2130 - Enterprise Cloud Architecture (ECA)
* **GCP Project ID:** intense-slice-505613-d3

## 📦 Production Deployment Architecture
The core business microservices are deployed inside a highly-available, auto-scaled Managed Instance Group (MIG) with private DB endpoints:
* **Microservices Instance Group:** `microservices-mig` (Zones: `asia-south1-a`, `asia-south1-b`)
* **Auto-Scaling Policy:** Min instances: 1, Max instances: 3, Target CPU Utilization: 60%
* **Cloud Storage Bucket:** `gs://bytebites-media-bucket-intense-slice-505613-d3` (Public read-only media bucket used by Menu Service and Delivery Service to upload food images and driver photos)
* **Datastore Database:** Native Cloud Datastore Mode in `asia-south1` Mumbai (Default Database)
* **Cloud SQL Databases (Private IP Peered):**
  * **MySQL Database:** `10.120.0.3:3006` (Schema: `bytebites_orders`)
  * **PostgreSQL Database:** `10.120.0.5:5432` (Schemas: `bytebites_delivery` and `bytebites_payments`)

## Project Description
This repository contains the core business logic microservices of the ByteBites Enterprise Food Delivery Application. It includes menu management, order placement, order dispatch and delivery tracking, and payment processing.

## Tech Stack
* **Language:** Java 17 / 25
* **Framework:** Spring Boot, Spring Cloud, Spring Data JPA
* **Microservices Ports:**
  * **Menu Service (Port 8081):** MongoDB locally / Datastore on GCP
  * **Order Service (Port 8082):** MySQL locally / Cloud SQL MySQL on GCP
  * **Delivery Service (Port 8083):** PostgreSQL locally / Cloud SQL Postgres on GCP
  * **Payment Service (Port 8084):** PostgreSQL locally / Cloud SQL Postgres on GCP

## Local Getting Started Instructions
To run these services locally, follow these steps:

### Prerequisites
1. Make sure you have JDK and Maven installed on your system.
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
