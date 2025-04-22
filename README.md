# 🛠️ Blog Application Backend

This is the **backend** for the Blog Application, built using **Spring Boot**. It provides RESTful APIs for user authentication, blog creation, and blog management, all secured with session-based authentication using Spring Security.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **MySQL**
- **Lombok**

---

## 📦 Features

- ✅ User registration & login with session-based authentication
- ✅ Blog CRUD operations (Create, Read, Update, Delete)
- ✅ Session management with `HttpOnly` cookies
- ✅ Secure endpoints using Spring Security
- ✅ Pagination for blog lists
- ✅ User-specific blogs
- ✅ CORS enabled for frontend integration
- ✅ Global exception handling

---

## ⚙️ Setup Instructions

### 🧱 Prerequisites

- Java 17 or above
- Maven
- MySQL running locally on port 3306

### 🛠️ Configuration

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_app
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
