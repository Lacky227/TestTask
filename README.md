# Test Task

Microservices architecture implementation for User Authentication and Data Processing.
Built with **Java 21**, **Spring Boot 4**, **PostgreSQL**, and **Docker**.

## Project Structure
* **auth-api**: Handles user registration, login (JWT), and processing logs.
* **data-api**: Performs data transformation (secured by internal token).
* **postgres**: Database service.

## Prerequisites
* Docker & Docker Compose

## How to Run
1. Clone the repository.
2. Build and start services:
   ```bash
   docker compose up -d --build
   ```
3. Wait for containers to be healthy (approx. 10-20 seconds).

## Testing (cURL Examples)
1. Register
   ```bash
   curl -X POST http://localhost:8080/api/user/register \ 
   -H "Content-Type: application/json" \ 
   -d '{"email":"test@example.com", "password":"password123"}'
   ```
2. Login (Get Token)
   ```bash
   curl -X POST http://localhost:8080/api/user/login \ 
   -H "Content-Type: application/json" \ 
   -d '{"email":"test@example.com", "password":"password123"}'
   ```
Copy the token from the response. 

3. Process Data (Requires Token)
   ```bash
   curl -X POST http://localhost:8080/api/process \ 
   -H "Authorization: Bearer YOUR_TOKEN_HERE" \ 
   -H "Content-Type: application/json" \ 
   -d '{"text":"hello world"}'
   ```

## Features
* Security: JWT Authentication, BCrypt password hashing.

* Architecture: REST API, Inter-service communication via RestClient.

* Database: PostgreSQL with automatic schema creation.

* Docker: Full containerization with Multi-stage builds and Healthchecks.

