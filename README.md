# 🔐 Fortify - Secure E-Commerce Backend (Spring Boot)

A secure backend system built using **Spring Boot, Spring Security, JWT Authentication, and Role-Based Access Control (RBAC)**.  
This project demonstrates real-world authentication, authorization, and protected API design.

---

## 🚀 Features

### 🔑 Authentication & Authorization
- User Registration & Login
- JWT Access Token generation
- Refresh Token support
- Secure password hashing using BCrypt
- Role-Based Access Control (RBAC)

### 👥 Roles Supported
- ADMIN
- CUSTOMER
- VENDOR

### 🔒 Security Features
- Spring Security integration
- Protected REST APIs
- Stateless authentication (JWT)
- Custom JWT Filter
- Method-level security (@PreAuthorize)

### 🔄 Token System
- Access Token (short-lived)
- Refresh Token (long-lived)
- Token validation and regeneration

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3+
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- PostgreSQL
- Maven

------

## 🔗 API Endpoints

### 🔐 Auth APIs

#### Register User
POST /api/auth/register

```json
{
  "name": "Kashish",
  "email": "admin@gmail.com",
  "password": "123456",
  "role": "ADMIN"
}
```
Login User

POST /api/auth/login

JSON
```
{
  "email": "admin@gmail.com",
  "password": "123456"
}
```
Response:
JSON
{
  "accessToken": "jwt-token",
  "refreshToken": "refresh-token"
}
Refresh Token

POST /api/auth/refresh
JSON
```
{
  "refreshToken": "your-refresh-token"
}
```
🔐 Protected API Example
Admin Only

GET /api/admin/dashboard
Customer Only

GET /api/customer/profile
Vendor Only

GET /api/vendor/products
🧪 Testing (Postman)
Step 1: Register user
Step 2: Login and get tokens
Step 3: Add token in header:
Authorization: Bearer <access_token>
Step 4: Call protected APIs

⚙️ Security Flow
User registers → data saved in DB
User logs in → JWT generated
JWT sent in Authorization header
Filter validates token
Role checked via Spring Security
Access granted or denied

📌 Important Notes
Email must be unique
Password is stored in encrypted form
Refresh token is stored separately
APIs are stateless (no session used)

🚀 Future Improvements
OAuth2 Login (Google/GitHub)
Redis-based token blacklist
Rate limiting (Resilience4j)
API Gateway integration
Multi-tenant architecture
