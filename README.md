# Student Course Management System – Backend

A complete **Spring Boot backend application** for managing **Students**, **Courses**, and their relationships using **RESTful APIs**.  
This project follows a **clean architecture** and is suitable for **freshers, internships, learning, and resume projects**.

---

## 📌 Project Overview

The Student Course Management System provides backend functionality to:
- Manage student data
- Manage course details
- Handle student–course relationships (enrollment)
- Perform full CRUD operations
- Expose REST APIs for frontend integration

This project can be easily integrated with **React / Angular / Mobile apps**.

---

## ✅ Features

- Student CRUD operations
- Course CRUD operations
- Student–Course mapping
- RESTful API design
- Spring Data JPA integration
- Hibernate ORM
- Maven-based project
- Database-driven backend
- Suitable for real-world use cases

---

## 🛠️ Technology Stack

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **Maven**
- **Oracle Database** (Configurable to MySQL / H2)
- **REST APIs**

---
_____

Student-Course-Backend
│
├── src
│ ├── main
│ │ ├── java
│ │ │ └── com.example.demo
│ │ │ ├── controller # REST Controllers
│ │ │ ├── service # Business Logic
│ │ │ ├── repository # JPA Repositories
│ │ │ └── entity # JPA Entities
│ │ └── resources
│ │ ├── application.properties
│ │ └── static
│ └── test
│
├── target
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md

---

## 🔧 Prerequisites

- Java 8 or Java 17+
- Maven (or Maven Wrapper)
- IDE (IntelliJ IDEA / Eclipse / VS Code)
- Oracle / MySQL / H2 Database

---

## ⚙️ Application Configuration

Update `application.properties`:

```properties
spring.application.name=Student-Course

server.port=3507

spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=student
spring.datasource.password=app123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

## 📂 Project Structure

