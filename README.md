📚 Library Management System

A complete backend application built using Java Spring Boot for managing library operations in a college.
The system allows students to issue and return books digitally while the admin controls all records securely.

🔧 Tech Stack

Java

Spring Boot

Spring Security

Spring Data JPA (Hibernate)

MySQL

Maven

Lombok


🧑‍💻 Run from IDE

Open project in IntelliJ/Eclipse

Wait for Maven dependencies to download

Run the Spring Boot main class

🧱 System Design

This project follows Monolithic Architecture and is divided into different modules:

📦 Core Entities
Entity	Description
Student	Stores student information
Card	Library card linked with student
Book	Stores book information
Author	Stores author information
User	Used for login and security
Transaction	Stores issue and return history


🔄 Main Features

👨‍🎓 Student Operations

Register new student

Update personal details

Delete account

Change login password

📚 Library Operations

Add / Update / Delete books

Add / Update / Delete authors

View all records

🔁 Book Issue & Return System
📕 Issue Book

/issueBook?bookId=&cardId=

Checks

Card must be active

Book must be available

Student should not exceed limit

Actions

Book marked unavailable

Card-book mapping created

Transaction saved with unique ID

📘 Return Book

/returnBook?bookId=&cardId=

Actions

Book marked available

Fine calculated automatically

Return transaction saved

🔐 Security Layer

The project uses Spring Security with role-based access control.

Role	Permissions
ADMIN	Full system access
STUDENT	Limited access to own data
Access Examples
API	Allowed Role
/student/all	ADMIN
/student/updateStudent	STUDENT
/student/changePassword	STUDENT
/transaction/all	ADMIN
/transaction/issueBook	STUDENT

Developed By
Vinay Mahangade