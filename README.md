# 📚 DREAMY — Library Management System

> *"Building a bridge between your library and your dreams."*

A modern RESTful API backend for managing books, authors, and categories — built with Java 17 and Spring Boot.

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot |
| ORM | JPA / Hibernate |
| Database (Dev/Prod) | MySQL |
| Database (Test) | H2 (in-memory) |
| Containerization | Docker (multi-stage Dockerfile) |
| CI/CD | GitHub Actions |
| Deployment | Render Cloud |

---

## 🏗️ Architecture

The project follows a clean layered architecture:

```
Controller Layer  →  Service Layer  →  Repository Layer  →  Database
       ↕                  ↕
      DTOs          Business Logic & Validation
```

**Key Patterns Used:**
- DTO Pattern (Request / Response separation)
- Repository Pattern
- Service Layer
- Global Exception Handling

---

## 📦 Entities

### Book
| Field | Type | Constraint |
|---|---|---|
| id | Long | Primary Key, Auto-increment |
| title | String | Not Null, Min 2 chars |
| isbn | String | Unique, Not Null |
| stockQuantity | Integer | Min 0 |
| author | Author | Many-to-One |
| category | Category | Many-to-One |

### Author
| Field | Type | Constraint |
|---|---|---|
| id | Long | Primary Key |
| fullName | String | Not Null |
| biography | String | Optional |

### Category
| Field | Type | Constraint |
|---|---|---|
| id | Long | Primary Key |
| name | String | Unique (e.g. Fiction, Science) |

---

## 🔌 API Endpoints

### Books — `/api/books`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/books` | Create a new book |
| `GET` | `/api/books` | List all books |
| `GET` | `/api/books/{id}` | Get book by ID |
| `PUT` | `/api/books/{id}` | Update book |
| `DELETE` | `/api/books/{id}` | Delete book |

### Authors — `/api/authors`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/authors` | Create a new author |
| `GET` | `/api/authors` | List all authors |
| `GET` | `/api/authors/{id}` | Get author by ID |
| `PUT` | `/api/authors/{id}` | Update author |
| `DELETE` | `/api/authors/{id}` | Delete author |

### Categories — `/api/categories`
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/categories` | Create a new category |
| `GET` | `/api/categories` | List all categories |
| `GET` | `/api/categories/{id}` | Get category by ID |
| `PUT` | `/api/categories/{id}` | Update category |
| `DELETE` | `/api/categories/{id}` | Delete category |

---

## ⚠️ Error Handling

All errors return a standardized JSON response via `ErrorResponse`.

| Exception | HTTP Status | Trigger |
|---|---|---|
| `BookNotFoundException` | `404 Not Found` | Book ID does not exist |
| `AuthorNotFoundException` | `404 Not Found` | Author ID is missing |
| `DuplicateIsbnException` | `409 Conflict` | ISBN already exists |
| `InvalidDataException` | `400 Bad Request` | Validation failure (null fields, negative stock, etc.) |

---

## ▶️ Running the Project

### Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose

### Run locally with Docker Compose

```bash
# Clone the repository
git clone https://github.com/your-org/dreamy-api.git
cd dreamy-api

# Start the application and MySQL
docker-compose up --build
```

The API will be available at: `http://localhost:8080`

### Run tests (H2 in-memory)

```bash
mvn test
```

---

## ⚙️ Environment Profiles

| Profile | Config File | Database |
|---|---|---|
| Test | `application-test.properties` | H2 (in-memory) |
| Production | `application-prod.properties` | MySQL (Render Cloud) |

---

## 👥 Team

| Member | Role | Responsibilities |
|---|---|---|
| **Yaqut** | Lead & Core | GitHub repo setup, project scaffolding, Author module, Docker Compose |
| **Shebnem** | Entity & Quality | Book module (relations), Global exception handling, Dockerfile |
| **Qurbanali** | Test & Logic | Category module, H2 integration tests, GitHub Actions CI, README |

---

## 📝 Development Notes

- **DTOs are mandatory** — never expose JPA entities directly in controller responses.
- **Validation** is enforced at the entry point using `@Valid`, `@NotBlank`, and `@Size`.
- **ISBN uniqueness** is validated at the service layer before persistence.
- **Stock quantity** cannot be negative (minimum value: 0).
