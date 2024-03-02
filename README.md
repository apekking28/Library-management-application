## Library Management System

**Brief Description:**
This project is a library management application built using Spring Boot as the main framework. The application provides login and registration functions with strict validation to ensure data security and accuracy. In addition, the application records book loans, provides information to the library admin about books that are overdue or still on loan, and ensures that each user can only borrow one book at a time.

**Technology Used:**
- Spring Boot: A Java framework that simplifies microservices-based application development.
- PostgreSQL: A powerful relational database management system.
- JPA (Java Persistence API): A Java technology that provides an object-relational mapping framework for interacting with databases.
- Docker: An open-source platform for automation of implementation, scalability, and management of containerized applications.

**Main Features:**
1. **Login and Register function:**
   - Email validation to ensure that the email domain is correct.
   - Password validation with certain requirements (8 alphanumeric characters, at least 1 capital letter, cannot contain special characters).
   - Prevention of registration with an already registered email.

2. **Book Borrowing Record:**
   - Users can record book borrowing, record data on books borrowed and the date of borrowing.

3. **Late Return Notification:**
   - Library admins will get notifications about books that have been returned late or are still on loan.

4. **Borrowing Restrictions:**
   - Each user is only allowed to borrow one book at a time.
   - Users must return the book first before they can borrow the book again.
  
**How to Run the Application with doocker:**
1. Clone the repository from GitHub:
   ```bash
   git clone git@github.com:apekking28/Library-management-application.git
   ```

2. Go to the project directory:
   ```bash
   cd Library-management-application
   ```
3. configure the app with a database
   ```bash
   docker compose up -d
   ```
### Note:
- You must have docker on your PC
- also ensure the location of the docker-compose.yml file

**How to Run the Application Manualy:**
1. Clone the repository from GitHub:
   ```bash
   git clone git@github.com:apekking28/Library-management-application.git
   ```

2. Go to the project directory:
   ```bash
   cd Library-management-application
   ```
3. configure the app with a database

With these steps, the project can be run and managed efficiently using Spring Boot, PostgreSQL, JPA, and Docker.

**API Documentation**
[Postman](https://documenter.getpostman.com/view/29820629/2sA2xb6Fvq)
