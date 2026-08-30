# Multi-Tenant User Management API

A Spring Boot REST API for managing **multiple organisations, users, roles, permissions, authentication, and audit logs** with strict tenant isolation.

## Features

* Multi-Tenant User Management
* JWT Authentication
* Role-Based Access Control (RBAC)
* System Admin & Super User management
* Password management & recovery
* Audit Logging
* Tenant-level data isolation

## Tech Stack

* Java / Spring Boot
* Spring Security
* Spring Data JPA
* Mysql
* JWT
* Maven
* Docker

## Git Workflow

All development should be done through feature branches and Pull Requests.

```bash
git checkout -b feature/<feature-name>
git push origin feature/<feature-name>
```

**Do not push directly to `main`.**

All Pull Requests will be reviewed by the **Team Lead** before merging.
