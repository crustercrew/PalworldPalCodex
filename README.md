# Palworld PalCodex API 🐾🔥⚡

A robust, high-performance RESTful API service built with Java 17 and Spring Boot for exploring, filtering, and analyzing Pal data from **Palworld**. Inspired by open-source gaming encyclopedias like PokéAPI, this project provides detailed endpoints for Pal stats, elemental match-ups, work suitabilities, drop items, and base worker recommendations.

---

## 🚀 Features

- 📖 **Comprehensive Pal Codex**: Search and filter Pals by name, element types, and work suitabilities.
- ⚔️ **Counter Pal Generator**: Query counter element matchups to build effective battle squads.
- 🛠️ **Base Worker Recommendations**: Identify top-performing Pals tailored to specific base production tasks.
- 📊 **Combat Analytics**: Rank and retrieve top attacking Pals based on base stats.
- ⚙️ **Database Migrations & Seeding**: Managed schema evolution using automated seeding logic.
- 🐳 **Docker & Container Ready**: Multi-stage Dockerized deployment with `docker-compose` orchestration for PostgreSQL.

---

## 🛠️ Tech Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Data & Persistence**: Spring Data JPA, PostgreSQL, Flyway Database Migrations
- **Containerization**: Docker, Docker Compose
- **Build Tool**: Maven

---

## ⚡ Quick Start & Setup

### Prerequisites
- Docker & Docker Compose
- JDK 17+ (optional, for local development without Docker)
- Maven 3.8+ (optional)

### Running with Docker Compose (Recommended)

1. **Clone the Repository**
```bash
git clone [https://github.com/crustercrew/PalworldPalCodex.git](https://github.com/crustercrew/PalworldPalCodex.git)
cd PalworldPalCodex
```
2. **Launch with Docker Compose**

```Bash
docker-compose up -d --build
```
The API will be available at http://localhost:8080.
Interactive OpenAPI Sandbox Testing UI: http://localhost:8080/swagger-ui/index.html

## 📌 API Endpoints Overview

| Method | Endpoint | Description |
|--------| -------  |-------------|
| GET    |/api/v1/works|Retrieve a list of all available work suitabilities (e.g., Mining, Kindling)|
| GET    |/api/v1/works/{workType}/pals|Fetch all Pals capable of performing a specific work type|
| GET    |/api/v1/pals|Retrieve a paged list of Pals with multi-attribute filtering options|
| GET    |/api/v1/pals/{palNumber}|Get detailed stats, elements, active skills, and loots for a specific Pal|
| GET    |/api/v1/pals/stats/top-attackers|Get top Pals ranked by highest base attack stats|
| GET    |/api/v1/pals/recommendations/counters|Find counter Pals against specific elemental types|
| GET    |/api/v1/pals/recommendations/base-workers|Get top recommended Pals optimized for base production tasks|
| GET    |/api/v1/pals/loots/search|Search Pals based on item drops/loots they yield|
| GET    |/api/v1/elements|Retrieve a list of all elemental types in Palworld|
| GET    |/api/v1/elements/{elementName}/pals|Fetch all Pals associated with a specific elemental type|

## 🧪 Running Tests
To run unit and integration tests locally:

```Bash
./mvnw clean test
```