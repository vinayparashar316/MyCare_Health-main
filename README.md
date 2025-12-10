# MyKare_Health

Built a User Management Application, where a user can register himself either as an ADMIN or as a USER with the application. This application uses Role-based authorization. all the exceptions are handled.



___________________________________________________________________________________________________________________________________________________________

# Tech Stacks: 

* Java
* Spring Boot
* Maven
* MySql
* SpringData Jpa
* Hibernate
* Spring Security(Basic auth)
* Devtools
* Lombok
* Swagger-ui

# Features :

* User registration
* User and Admin authentication
* Role-based authorization

# Admin Features:

* Can find all users.
* Can find a particular user based on email id.
* Can access the details of different users.
* Can delete a user based on email id.

# User Features:

* Register with the application and sign in.
* Can find a particular user based on email id.



# Installation & Run

Before running the API server, you should configure your database inside the application.properties file.
Update the port number, username and password as per your local database config. 

`server.port=8000
spring.datasource.url=jdbc:mysql://localhost:3306/mykaredb 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=mysql username 
spring.datasource.password=YourPassword 
spring.jpa.hibernate.ddl-auto=update`

# Endpoints
API Root Endpoint - `https://localhost:8800`

Swagger enpoint: `http://localhost:8000/swagger-ui.html`

* (POST) To register user: `https://localhost:8000/user/register`  (Can be accessed by all)
* (GET) To get all users: `https://localhost:8000/user/all`  (Can be accessed by ADMIN only)
* (GET) To get a particular user: `https://localhost:8000/user/{email}` (Can be accessed by all)
* (DELETE) To delete a user: `https://localhost:8000/user/{email}` (Can be accessed by ADMIN only)

REQUEST BODY for registration
`{
    "name": "Your Name",
    "email": "youremail@gmail.com",
    "gender": "MALE",
    "password": "pasword",
    "role": "user/admin"
}`
