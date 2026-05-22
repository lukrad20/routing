# Spring Boot Routing API

---

# Overview

The application runs on port `8080` and exposes the following endpoint:

```http
GET /routing/{origin}/{destination}
```

Example:

```http
GET http://localhost:8080/routing/CZE/FRA
```

---

# Prerequisites

Make sure the following tools are installed:

- Java 21+
- Maven 3.8+
- Git

---

# Getting Started

## Clone the Repository

```bash
git clone https://github.com/lukrad20/routing.git
cd routing
```

## Build the Project

```bash
mvn clean install
```

## Run the Application

```bash
mvn spring-boot:run
```

Application will start at:

```text
http://localhost:8080
```
