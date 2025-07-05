<a id="readme-top"></a>

# 📚 IELTS Learning Backend System

<!-- TABLE OF CONTENTS -->

## 🚩 Table of Contents

-   [🧠 About The Project](#-about-the-project)
-   [🛠️ Built With](#️-built-with)
-   [⚙️ Getting Started](#-getting-started)
    -   [📋 Prerequisites](#-prerequisites)
    -   [📦 Installation](#-installation)
    -   [🚀 Usage](#-usage)
-   [📄 API Documentation](#-api-documentation)
-   [🗺️ Roadmap](#️-roadmap)
-   [🤝 Contributing](#-contributing)
-   [📜 License](#-license)
-   [📬 Contact](#-contact)
-   [🙏 Acknowledgements](#-acknowledgements)

<!-- ABOUT THE PROJECT -->

## 🧠 About The Project

This project is a **backend system** for an **IELTS learning platform**, designed as a **Spring Boot microservices architecture**.
It leverages **gRPC** for high-performance inter-service communication, **Docker** for easy deployment, and **OpenAPI** for automated API documentation.

🔍 **Key Features**:

-   ✅ **Spring Boot microservices** for scalable module separation.
-   ✅ **gRPC** for efficient inter-service communication.
-   ✅ **Dockerized** for easy deployment and orchestration.
-   ✅ **API documentation** fully managed by **OpenAPI/Swagger**.
-   ✅ **PostgreSQL** as the primary relational database.
-   ✅ Clean, modular project structure.
-   ✅ Easily extensible for new services.

This backend provides robust foundations for handling user progress, test modules, scoring logic, and future expansion.

---

<p align="right"><a href="#readme-top">⬆️</a></p>

## 🛠️ Built With

-   [Java 21](https://www.oracle.com/java/)
-   [Spring Boot](https://spring.io/projects/spring-boot)
-   [gRPC](https://grpc.io/)
-   [Docker](https://www.docker.com/)
-   [OpenAPI](https://swagger.io/specification/)
-   [PostgreSQL](https://www.postgresql.org/)

---

<p align="right"><a href="#readme-top">⬆️</a></p>

<!-- GETTING STARTED -->

## 🚀 Getting Started

Follow these steps to run the project locally.

### 📋 Prerequisites

Ensure you have the following installed on your machine:

-   **Java 17** or **21**
-   **Maven**
-   **Docker & Docker Compose**
-   **PostgreSQL** (if not using Docker for DB)
-   **Git**

Check Java version:

```bash
java -version
```

Check Docker:

```bash
docker --version
docker-compose --version
```

### 📦 Installation

1. Clone the repository [IELTS Learning Backend System](https://github.com/Kant2510/ielts-learning-backend-system)
    ```bash
    git clone https://github.com/Kant2510/ielts-learning-backend-system
    cd ielts-learning-backend
    ```
2. Build the project
    ```bash
    ./mvnw clean install
    ```
3. Run with Docker Compose
    ```bash
    docker-compose up --build
    ```

---

<p align="right"><a href="#readme-top">⬆️</a></p>

<!-- USAGE EXAMPLES -->

### 📜 Usage

-   Check running containers:
    ```bash
    docker ps
    ```
-   Inspect logs:
    ```bash
    docker logs <container_name>
    ```
-   Access API (auth, user, quiz):
    ```
    http://localhost:5000/api/v1/...
    ```

---

<p align="right"><a href="#readme-top">⬆️</a></p>

## 📄 API Documentation

All endpoints are documented using **OpenAPI**.

Swagger UI is available at:

```
http://localhost:5000/api-docs/...
```

---

<p align="right"><a href="#readme-top">⬆️</a></p>

## 🗺️ Roadmap

| Status | Feature                                               |
| ------ | ----------------------------------------------------- |
| ✅     | Core microservices implementation                     |
| ✅     | gRPC integration                                      |
| ✅     | PostgreSQL database                                   |
| ✅     | Docker & Docker Compose                               |
| ✅     | OpenAPI documentation                                 |
| 🚧     | Deployment pipeline (CI/CD)                           |
| 🚧     | Production-ready Docker images                        |
| 🚧     | Kubernetes manifests for staging/production           |
| 🚧     | Cloud deployment scripts                              |
| 🚧     | Monitoring & logging stack (Prometheus, Grafana, ELK) |

---

<p align="right"><a href="#readme-top">⬆️</a></p>

## 🤝 Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create.  
Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

<p align="right"><a href="#readme-top">⬆️</a></p>

## 📬 Contact

-   Email: [auletuannhat@gmail.com](mailto:auletuannhat@gmail.com)
-   Github: [Kant2510](https://github.com/Kant2510/ielts-learning-backend-system)
-   LinkedIn: [Nhat Au](https://www.linkedin.com/in/nhat-au-73a629283)

---

<p align="right"><a href="#readme-top">⬆️</a></p>

## 🙏 Acknowledgements

-   [Spring Boot](https://spring.io/)
-   [gRPC](https://grpc.io/)
-   [Docker](https://www.docker.com/)
-   [OpenAPI](https://swagger.io/)
-   [PostgreSQL](https://www.postgresql.org/)

---

<p align="right"><a href="#readme-top">⬆️</a></p>

**Happy coding!** 🚀✨
