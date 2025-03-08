# Deployment Guide

## Prerequisites

- Java 11 or higher
- Maven
- PostgreSQL

## Steps

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/updevlogics/e-dastavej.git
    cd e-dastavej
    ```

2. **Configure Database**:
    - Update the `application.properties` file with your PostgreSQL database configuration.
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/edastavej
    spring.datasource.username=postgres
    spring.datasource.password=postgrespassword
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the Application**:
    ```bash
    mvn clean install
    ```

4. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

5. **Access the Application**:
    - Open your browser and navigate to `http://localhost:8080`.