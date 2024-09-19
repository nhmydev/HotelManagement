
# Hotel Management
Welcome to the Hotel Management Repository! This project provides a solution for managing hotel operations, including room reservations, guest management, and booking handling.



## Features
This system supports:

- Room Management: Track and manage room availability, types, and pricing.
- Guest Management: Login and register
- Booking System: Handle reservations




## Tech Stack

**Client:** Angular, TailwindCSS

**Server:** Java Spring Boot

**Database:** MySql 




## Run Locally

Clone the project

```bash
  git clone https://github.com/nhmydev/HotelManagement.git
```

Navigate to the Project Directory:

```bash
  cd HotelManagement
```

Navigate to the Java Server Directory:
```bash
  cd Backend/HotelManagement
```
 Build and Run the Java Application:

```bash
  ./mvnw spring-boot:run
```
Ensure that the Spring Boot application is running on the expected port (typically http://localhost:8080).


Install Dependencies for Angular:

```bash
  npm install
```

Start the Angular Development Server

```bash
  ng serve
```


## Additional Notes
Ensure that the database connection details in src/main/resources/application.properties are correctly configured. Update the following properties according to your database setup:


```bash
 spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase
 spring.datasource.username=yourusername
 spring.datasource.password=yourpassword
```
