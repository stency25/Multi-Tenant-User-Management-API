# Multi-Tenant User Management API

A secure REST API for managing **multi-tenant organisations, users, roles, permissions, authentication, and audit logs**.

## Features

* Multi-tenant organisation management
* System Admin, Super User & Standard User roles
* JWT authentication & RBAC
* Tenant-level data isolation
* User and role management
* Password change & password recovery
* Administrative password resets
* Audit logging with sensitive-data redaction
* Swagger/OpenAPI documentation

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA / Hibernate
* MySQL
* JWT
* Lombok
* SpringDoc OpenAPI
* Maven

## API Groups

```text
/api/v1/system/*    → System Admin operations
/api/v1/tenant/*    → Tenant user & role management
/api/v1/audit/*     → Audit logs
```

## Database

Main entities:

```text
organisations
users
roles
permissions
user_roles
role_permissions
audit_logs
```

Tenant-bound records use `organisation_id` to enforce data isolation.

## Run Locally

Create the database:

```sql
CREATE DATABASE multi_tenant_db;
```

Configure your database credentials in:

```text
src/main/resources/application.yml
```

Run:

```bash
mvn clean install
mvn spring-boot:run
```

Application:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

## Authentication

Protected endpoints require:

```text
Authorization: Bearer <JWT_TOKEN>
```

Passwords are securely hashed and sensitive credentials are excluded from logs and audit records.


## Git Workflow

All development should be done through feature branches and Pull Requests.

```bash
git checkout -b feature/<feature-name>
git push origin feature/<feature-name>
```

**Do not push directly to `main`.**

All Pull Requests will be reviewed by the **Team Lead** before merging.
