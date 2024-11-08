# Spaceship Service API

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)

## Introduction

Welcome to the **Spaceship Service API**! This RESTful API allows you to manage a fleet of spaceships, providing endpoints for creating, reading, updating, deleting, and searching spaceships. It also includes user authentication using JWT tokens to secure access to the API.

## Features

- **User Authentication**: Secure login using JWT tokens.
- **CRUD Operations**: Create, read, update, and delete spaceship records.
- **Search Functionality**: Search spaceships by name with pagination and sorting.
- **Error Handling**: Comprehensive error responses for invalid requests.
- **API Documentation**: Integrated Swagger UI for exploring the API.

## Technologies Used

- **Java 21**
- **Spring Boot 3.3**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **MapStruct**: For object mapping.
- **Hibernate/JPA**: For database interactions.
- **PostgresSQL**: For manage data.
- **JUnit 5** & **Mockito**: For testing.
- **Swagger/OpenAPI**: For API documentation.

## Getting Started

### Prerequisites

- **Java 21** or higher installed.
- **Maven** installed.
- **Docker** installed.
- **IDE** like IntelliJ IDEA (recommended).

### Installation

1. **Clone the Repository**

    ```bash
    git clone https://github.com/yourusername/spaceship-service.git
    cd spaceship-service
    ```

2. **Run the Application**

    ```bash
   cd /docker
   docker-compose up
    ```

   The application will start on `http://localhost:8080`.


3. **Access the API Documentation**

    Open your browser and navigate to `http://localhost:8080/swagger-ui/index.html`.


4. **Test the API**

    The credentials to log in and test the API are:

    ```bash
    username: admin
    password: password
    ``` 

