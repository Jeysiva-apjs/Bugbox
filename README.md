# Bugbox - Bug Tracking Application

Bugbox is a robust bug tracking application built using **Java 21** and **Spring Boot**, with **Spring Security**, **JWT authentication**, and **validation**. It allows users to report bugs, manage bug statuses, and handle user roles efficiently. Admins can manage users and bugs, while users can create and track bug reports.


## Features

### User Features
- Create and report bugs.
- Update details of existing bugs.
- View all reported bugs.

### Admin Features
- Manage users:
  - Promote users to admin role.
  - View all users.
  - Delete non-admin users.
- Manage bugs:
  - Assign bugs to developers.
  - Toggle bug resolved status.
  - View all bugs and filter by priority.
  - Delete bugs.

### Authentication
- Register as a new user or admin.
- Login using JWT-based authentication.


## REST API Endpoints

### User - Reporting Bugs
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | `/bugs` | Create a new bug |
| GET    | `/bugs` | Get all bugs |
| PUT    | `/bugs/{id}` | Update bug details |

### User - Service
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/api/users/info` | Get current user information |
| PUT    | `/api/users/password` | Update user password |

### Admin - User Management
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/admin/users` | Get all users |
| PUT    | `/admin/users/{userId}/role` | Promote user to admin |
| DELETE | `/admin/users/{userId}` | Delete a non-admin user |

### Admin - Bug Management
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET    | `/admin/bugs` | Get all bugs |
| GET    | `/admin/bugs/priority` | Get all bugs by priority |
| PUT    | `/admin/bugs/{id}/status` | Toggle bug resolved status |
| PUT    | `/admin/bugs/{id}/assign` | Assign bug to a developer |
| DELETE | `/admin/bugs/{id}` | Delete a bug |

### Authentication
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | `/auth/register/user` | Register as a new user |
| POST   | `/auth/register/admin` | Register as a new admin |
| POST   | `/auth/login` | Login a user |


## Tech Stack

- **Backend:** Java 21, Spring Boot  
- **Security:** Spring Security, JWT Authentication  
- **Persistence:** JPA / Hibernate, MySQL  
- **Validation:** Bean Validation (Jakarta Validation API)  

