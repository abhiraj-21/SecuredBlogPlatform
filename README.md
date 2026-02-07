<div align="center">

  <h1>📝 Spring Boot Blog CMS API</h1>

  <p>
    A production-grade RESTful API for a Content Management System (CMS). <br />
    Built with <strong>Java 21</strong>, <strong>Spring Boot 4</strong>, and <strong>MySQL</strong>.
  </p>

  <p>
    <a href="https://github.com/abhiraj-21/spring-boot-blog-api/graphs/contributors">
      <img src="https://img.shields.io/badge/contributors-1-orange?style=flat-square" alt="Contributors" />
    </a>
    <a href="https://github.com/abhiraj-21/spring-boot-blog-api/network/members">
      <img src="https://img.shields.io/badge/forks-0-blue?style=flat-square" alt="Forks" />
    </a>
    <a href="https://github.com/abhiraj-21/spring-boot-blog-api/stargazers">
      <img src="https://img.shields.io/badge/stars-0-yellow?style=flat-square" alt="Stars" />
    </a>
    <a href="https://github.com/abhiraj-21/spring-boot-blog-api/blob/master/LICENSE">
      <img src="https://img.shields.io/badge/license-MIT-green?style=flat-square" alt="License" />
    </a>
  </p>

  <h4>
    <a href="#-tech-stack">Tech Stack</a> •
    <a href="#-key-features">Features</a> •
    <a href="#-getting-started">Getting Started</a> •
    <a href="#-api-documentation">API Docs</a>
  </h4>
</div>

---

## 📖 About The Project

This backend application serves as a robust foundation for blogging platforms or CMS applications. It is designed to handle real-world scenarios including **Role-Based Access Control (RBAC)**, **JWT Authentication**, and high-performance database interactions.

Unlike simple tutorials, this project addresses critical architectural challenges:
* **Security:** Stateless authentication using JWT and BCrypt password hashing.
* **Performance:** Solved the N+1 query problem using JPA Entity Graphs and JPQL projections.
* **Scalability:** Implements pagination, sorting, and DTO patterns to decouple the internal domain from the API layer.

---

## 🛠 Tech Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Java 21 |
| **Framework** | Spring Boot 4 (Web, Security, Data JPA) |
| **Database** | MySQL 8.0 |
| **Security** | Spring Security, JWT (JJWT), BCrypt |
| **Build Tool** | Maven |
| **Docs** | Swagger / OpenAPI 3 |
| **Utils** | Lombok, Hibernate Validator |

---

## 🚀 Key Features

### 🔐 Authentication & Security
* **User Registration & Login:** Secure flow with encrypted passwords.
* **JWT Auth:** Stateless session management with Bearer tokens.
* **RBAC:** Granular permissions for `USER` and `ADMIN` roles.
* **Ownership Security:** Middleware prevents users from deleting/editing content they don't own.

### 📝 Content Management
* **CRUD Operations:** Full lifecycle management for Posts and Comments.
* **Advanced Fetching:**
    * Pagination (`?page=0&size=10`)
    * Sorting (`?sort=createdAt,desc`)
    * Keyword Search for posts.
* **Validation:** Strict payload validation (`@NotBlank`, `@Size`, `@Email`).

### 🛡️ Admin Controls
* **User Analytics:** View all users with aggregated post/comment counts in a single query.
* **Moderation:** Force-delete any post or comment violating platform rules.

### ⚡ Performance Optimizations
* **N+1 Query Fix:** Utilized `@EntityGraph` for fetching comments with users.
* **Tuple Projections:** Optimized admin dashboard queries to fetch stats without loading entire entity graphs.

---

## 🏁 Getting Started

Follow these steps to set up the project locally.

### Prerequisites
* Java Development Kit (JDK) 21
* Maven 3.8+
* MySQL Server running on port `3306`

### Installation

1.  **Clone the Repository**
    ```bash
    git clone [https://github.com/abhiraj-21/spring-boot-blog-api.git](https://github.com/abhiraj-21/spring-boot-blog-api.git)
    cd spring-boot-blog-api
    ```

2.  **Configure Database**
    * Create a database named `blog_db` in MySQL Workbench or CLI:
        ```sql
        CREATE DATABASE blog_db;
        ```
    * Update `src/main/resources/application.properties` with your credentials:
        ```properties
        spring.datasource.username=root
        spring.datasource.password=your_password
        ```

3.  **Build and Run**
    ```bash
    mvn spring-boot:run
    ```

4.  **Access the Application**
    The server will start at `http://localhost:8080`.

---

## 🔌 API Documentation

Detailed API documentation is available via **Swagger UI** once the application is running:
> **URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Quick Reference

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| **Auth** | | | |
| `POST` | `/api/auth/register` | Register new user | Public |
| `POST` | `/api/auth/login` | Login & get Token | Public |
| **Posts** | | | |
| `GET` | `/api/posts` | Get all posts | Public |
| `POST` | `/api/posts` | Create post | Auth |
| `PUT` | `/api/posts/{id}` | Update post | Author Only |
| `DELETE` | `/api/posts/{id}` | Delete post | Author / Admin |
| **Admin** | | | |
| `GET` | `/api/admin/users` | User stats | Admin Only |

---

## 🤝 Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

---

## 📜 License

Distributed under the MIT License. See `LICENSE` for more information.

---

<div align="center">
  <p>Made with ❤️ by <strong>Abhiraj Singh</strong></p>
  <a href="https://github.com/abhiraj-21">
    <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  </a>
  <a href="https://www.linkedin.com/in/abhiraj07">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
</div>
