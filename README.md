# Dreamy 📚
A production-ready Spring Boot REST API for managing a digital bookstore — authors, categories, and books. Built with Java 17, MySQL (local), PostgreSQL (production), and deployed on Render.

🚀 Live Demo: https://dreamy-h82a.onrender.com

📋 Project Board: [Trello](https://trello.com/b/nN19nN3i/devops-project)

## API Reference
Base URL: `/api`

All requests and responses use `Content-Type: application/json`.

---

### Authors

#### Get All Authors
`GET /api/authors`

Responses:
- `200 OK` — returns list of authors as JSON

#### Create an Author
`POST /api/authors`

Request body:
```json
{
  "fullName": "George Orwell",
  "biography": "English novelist famous for 1984 and Animal Farm."
}
```

Responses:
- `201 Created` — returns the created author as JSON
- `400 Bad Request` — validation failed
- `409 Conflict` — author with that name already exists

#### Delete an Author
`DELETE /api/authors/{id}`

Responses:
- `204 No Content` — author deleted successfully
- `404 Not Found` — author with that ID does not exist

---

### Categories

#### Get All Categories
`GET /api/categories`

Responses:
- `200 OK` — returns list of categories as JSON

#### Create a Category
`POST /api/categories`

Request body:
```json
{
  "name": "Fantasy"
}
```

Responses:
- `201 Created` — returns the created category as JSON
- `400 Bad Request` — validation failed
- `409 Conflict` — category with that name already exists

#### Delete a Category
`DELETE /api/categories/{id}`

Responses:
- `204 No Content` — category deleted successfully
- `404 Not Found` — category with that ID does not exist

---

### Books

#### Get All Books
`GET /api/books`

Responses:
- `200 OK` — returns list of all books as JSON

#### Get a Book by ID
`GET /api/books/{id}`

Responses:
- `200 OK` — returns the book as JSON
- `404 Not Found` — book with that ID does not exist

#### Create a Book
`POST /api/books`

Request body:
```json
{
  "title": "1984",
  "isbn": "978-0-452-28423-4",
  "stockQuantity": 85,
  "authorId": 1,
  "categoryId": 2
}
```

Responses:
- `201 Created` — returns the created book as JSON
- `400 Bad Request` — validation failed
- `409 Conflict` — book with that ISBN already exists

#### Update a Book
`PUT /api/books/{id}`

Request body:
```json
{
  "title": "Nineteen Eighty-Four",
  "stockQuantity": 100,
  "authorId": 1,
  "categoryId": 2
}
```

Responses:
- `200 OK` — returns the updated book as JSON
- `400 Bad Request` — validation failed
- `404 Not Found` — book with that ID does not exist

#### Delete a Book
`DELETE /api/books/{id}`

Responses:
- `204 No Content` — book deleted successfully
- `404 Not Found` — book with that ID does not exist

---

## Response Format

All entities include a `createdAt` timestamp field that is automatically set on creation:

```json
{
  "id": 1,
  "title": "1984",
  "isbn": "978-0-452-28423-4",
  "stockQuantity": 85,
  "authorName": "George Orwell",
  "categoryName": "Dystopian",
  "createdAt": "2026-05-15T06:39:05.152444"
}
```

---

## Local Development (Docker)

### Prerequisites
- Docker installed

### Run locally
Docker Compose starts both MySQL and the app with a single command:

```bash
git clone https://github.com/yagutrslbyl/Dreamy.git
cd dreamy
docker compose up --build
```

The API will be available at `http://localhost:8080`.

### Stop everything
```bash
docker compose down
```

---

## Spring Profiles

| Profile | Database | Used for |
|---------|----------|----------|
| default | MySQL on localhost:3306 | Local development |
| test | H2 in-memory | Integration tests |
| prod | PostgreSQL (Render) | Cloud deployment |

---

## CI/CD

GitHub Actions runs on every push or PR to main:

1. Checkout code
2. Set up Java 17
3. Cache Maven dependencies
4. Run `mvn clean verify` (builds + runs all tests with H2)

The pipeline status is shown as a badge on the repo.

---

## Deployment (Render)

### Step 1 — Create a PostgreSQL Database
1. Go to [dashboard.render.com](https://dashboard.render.com)
2. Click "New +" → select PostgreSQL
3. Configure:

| Field | Value |
|-------|-------|
| Name | dreamy-db |
| Region | e.g. Frankfurt (EU Central) — note it for Step 2 |
| PostgreSQL Version | 16 |

4. Click "Create Database" and wait until status is Available
5. Copy the Internal Database URL — you'll need it in Step 2

> ⚠️ **Important:** the database and the web service must be deployed to the same region. Render's internal networking only works within a region.

### Step 2 — Deploy the Web Service
1. Click "New +" → select Web Service
2. Connect your GitHub account and select your repository
3. Configure:

| Field | Value |
|-------|-------|
| Name | dreamy |
| Region | Same as your database |
| Environment | Docker |
| Dockerfile Path | ./Dockerfile |

4. Under the "Environment" tab, add:

| Key | Value |
|-----|-------|
| DATABASE_URL | from Render DB |
| DB_USERNAME | from Render DB |
| DB_PASSWORD | from Render DB |
| SPRING_PROFILES_ACTIVE | prod |

5. Click "Create Web Service"

### Auto-deploys
Every push to `main` triggers a new build and deploy automatically.

---

## Contributors

| Name | GitHub |
|------|--------|
| Yagut Resulbəyli | [@yagutrslbyl](https://github.com/yagutrslbyl) |
| Qurbanəli | [@gurban200OK](https://github..com/gurban200OK) |
| Şəbnəm | [@shebnem-m](https://github.com/shebnem-m) |