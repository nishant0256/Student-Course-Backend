# 🎓 Student Course Management System – Backend

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![REST API](https://img.shields.io/badge/API-REST-yellow)
![Status](https://img.shields.io/badge/Status-Active-success)

A **Spring Boot REST API** project for managing **Students**, **Courses**, and **Enrollments**.  
This backend application is designed for **learning**, **real-world practice**, and **resume-ready projects**.

---

## 📌 Table of Contents
- Project Overview
- Features
- Technology Stack
- Project Structure
- Prerequisites
- Configuration
- How to Run
- API Endpoints
- Sample Requests & Responses
- Testing
- Contributor
- Future Enhancements
- License

---

## 🚀 Project Overview

The **Student Course Management System** backend allows you to:
- Perform CRUD operations on students and courses
- Enroll students into courses
- Build scalable REST APIs
- Integrate easily with frontend frameworks like React or Angular

The project follows a **layered architecture** using Controller, Service, Repository, and Entity layers.

---

## ⭐ Features

✔️ Student CRUD operations  
✔️ Course CRUD operations  
✔️ Student–Course enrollment  
✔️ RESTful API design  
✔️ Spring Data JPA & Hibernate  
✔️ Maven-based build  
✔️ Configurable database support  

---

## 🛠️ Technology Stack

| Layer | Technology |
|------|------------|
| Language | Java |
| Framework | Spring Boot |
| ORM | JPA / Hibernate |
| Database | Oracle / MySQL / H2 |
| Build Tool | Maven |
| API Style | REST |

---

## 📂 Project Structure

Student-Course-Backend
├── src/main/java/com/example/demo
│ ├── controller
│ ├── service
│ ├── repository
│ └── entity
├── src/main/resources
│ └── application.properties
├── src/test/java
├── pom.xml
└── README.md


---

## 🔧 Prerequisites

- Java 8 or Java 17+
- Maven or Maven Wrapper
- Oracle / MySQL / H2 database
- IDE (IntelliJ IDEA / Eclipse / VS Code)

---

## ⚙️ Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.application.name=Student-Course
server.port=3507

spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=student
spring.datasource.password=app123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
