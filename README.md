# Clinic REST API

A backend REST API for managing core clinic operations, built with **Java 17** and **Spring Boot 3**.

The project demonstrates a production-oriented API structure with authentication, persistence, validation, pagination, database migrations, and centralized exception handling.

## Highlights

- RESTful endpoints for doctors and patients
- JWT-based authentication
- Stateless security configuration with Spring Security
- Persistence with Spring Data JPA
- Request validation with Jakarta Validation
- Pagination for collection endpoints
- Logical deletion for doctor records
- Centralized API error handling
- Database versioning with Flyway
- MySQL integration
- DTOs implemented with Java records

## Technology Stack

- Java 17
- Spring Boot 3.1
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Flyway
- Jakarta Validation
- Maven
- Lombok

## Project Structure

```text
src/main/java/com/clinic/api
├── controller
│   ├── AutenticationController
│   ├── MedicoController
│   └── PacienteController
├── domain
│   ├── consulta
│   ├── direccion
│   ├── medico
│   ├── paciente
│   └── usuario
└── infra
    ├── errores
    └── security
```

The application separates HTTP controllers, domain models and repositories, and infrastructure concerns such as authentication and exception handling.

## API Capabilities

### Authentication

`POST /login`

Authenticates a user and returns a JWT used to access protected endpoints.

### Doctors

- `POST /medicos` — create a doctor
- `GET /medicos` — list active doctors with pagination
- `GET /medicos/{id}` — retrieve a doctor by ID
- `PUT /medicos` — update doctor information
- `DELETE /medicos/{id}` — logically deactivate a doctor

### Patients

- `POST /pacientes` — create a patient
- `GET /pacientes` — list patients with pagination

## Security Design

The API uses stateless authentication with Spring Security.

- The login endpoint is publicly accessible.
- All remaining endpoints require authentication.
- Passwords are handled with BCrypt.
- JWT tokens are used to authenticate requests without server-side sessions.
- A custom security filter validates authentication before protected requests are processed.

## Validation and Error Handling

Incoming request models use Jakarta Validation constraints.

A centralized `@RestControllerAdvice` handles common API errors, including:

- `404 Not Found` when an entity does not exist
- `400 Bad Request` with field-level validation details when request data is invalid

This keeps controller logic focused on application behavior and provides consistent HTTP responses.

## Persistence and Database Migrations

The application uses Spring Data JPA for persistence and MySQL as the relational database.

Flyway is included to keep database schema changes versioned and reproducible across environments.

## Running the Project

### Requirements

- Java 17+
- Maven 3.8+
- MySQL

### Configuration

Configure your database connection and authentication secret using local application properties or environment-specific configuration before starting the API.

### Start

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

## Why This Project Matters

This project demonstrates several practices I use when building backend systems:

- Clear separation of responsibilities
- Explicit request validation
- Secure stateless authentication
- Consistent API error responses
- Database migration management
- Pagination for scalable collection endpoints
- Domain-focused DTOs instead of exposing persistence entities directly

It is a compact example of how I structure a maintainable Spring Boot REST API while keeping security, persistence, and HTTP concerns separated.

## Author

**Moises Romero**  
Senior Software Engineer  
GitHub: https://github.com/MROMERO2100
