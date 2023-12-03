# Spring Email

This simple application is designed to handle user registration by sending email verification to users. It is a RESTful
application built using the Spring Boot framework.

## Features

- User Registration: Allows users to register by providing necessary information.
- Email Verification: Sends a verification email to the registered user to confirm their identity.
- RESTful API: Exposes endpoints for easy integration with other applications.

## Technologies Used

- **Spring Boot**: A powerful and convention-over-configuration, opinionated framework for building Java-based
  enterprise applications.
- **JavaMail API**: Used for sending email messages.
- **RESTful Architecture**: Implements a RESTful design for seamless communication between the client and server.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/LuizVictor/spring-email.git
   ```

2. Navigate to the project directory:

   ```bash
   cd spring-email
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install -DskipTests
   ```

4. Run the application:

   ```bash
   java -jar target/springemail-0.0.1-SNAPSHOT.jar
   ```

The application will start, and you can access it at [http://localhost:8080](http://localhost:8080).

## Usage

1. Register a new user by making a `POST` request to `/api/users` with user details.
2. An email verification link will be sent to the provided email address.
3. Click on the verification link to complete the registration process.

## API Endpoints

- `POST /api/users`: Register a new user.
- `GET /api/users/validate?token={verificationToken}`: Validate user email by clicking on the provided link.

## Configuration

You can configure the database connection, email server settings, and other application properties in
the `application.yml` file.

```yml
# Database Configuration
datasource:
    url: your-database-url
    username: database-user
    password: database-pass
# Email Configuration
mail:
    host: your-smtp-server
    port: your-smtp-port
    username: your-smtp-user
    password: your-smtp-pass
```

## Contributing

Feel free to contribute to the development of this application by submitting pull requests or reporting issues.