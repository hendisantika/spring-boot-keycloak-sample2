# Spring Boot 4 Keycloak, Redis Cache & Rate Limiting

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-keycloak-sample2/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-keycloak-sample2/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)
![Keycloak](https://img.shields.io/badge/Keycloak-26-4D4D4D?logo=keycloak)
![Redis](https://img.shields.io/badge/Redis-7-DC382D?logo=redis)

Sample REST API (blog: authors, posts, tags) secured with **Keycloak** (OAuth2 resource server / JWT),
using **Redis** for caching and **Redisson + Bucket4j** for per-IP rate limiting.

## Tech Stack

| Component        | Version / Library                                         |
|------------------|-----------------------------------------------------------|
| Java             | 25 (Temurin)                                              |
| Spring Boot      | 4.1.x (Web MVC, Data JPA, Validation, Actuator, Cache)    |
| Security         | Spring Security OAuth2 Resource Server + Keycloak 26      |
| Cache            | Spring Cache + Redis (via Redisson)                       |
| Rate limiting    | Bucket4j (JCache / Redisson) + AspectJ, 20 req/min per IP |
| Database         | H2 in-memory (`schema.sql`, `data.sql`)                   |
| API docs         | springdoc-openapi (Swagger UI)                            |
| Build            | Maven Wrapper                                             |

## Prerequisites

- JDK 25
- Docker & Docker Compose

## Running Locally

Keycloak and Redis are defined in [`compose.yaml`](compose.yaml). Thanks to `spring-boot-docker-compose`,
they are started automatically when you run the application:

```shell
./mvnw spring-boot:run
```

Or start them manually:

```shell
docker compose up -d
```

| Service  | URL / Port                     | Credentials                                      |
|----------|--------------------------------|--------------------------------------------------|
| App      | http://localhost:8080/api      | Bearer token from Keycloak                       |
| Keycloak | http://localhost:7080          | `admin` / `admin`                                |
| Redis    | `localhost:6379`               | password from `spring.data.redis.password`       |

### Keycloak setup

1. Open http://localhost:7080 and log in as `admin` / `admin`.
2. Create realm `Power-Ranger` (see `spring.security.oauth2.resourceserver.jwt.issuer-uri`).
3. Create client `yellow-ranger` with client roles `admin` and `user`.
4. Create users and assign the client roles.

References:

- https://www.keycloak.org/getting-started/getting-started-docker
- https://medium.com/geekculture/using-keycloak-with-spring-boot-3-0-376fa9f60e0b

## Endpoints

All endpoints are served under the `/api` servlet path.

| Resource  | Endpoints                                                                          |
|-----------|------------------------------------------------------------------------------------|
| Authors   | `GET/POST /v1/authors`, `GET/DELETE /v1/authors/{id}`                              |
| Posts     | `GET/POST /v1/posts`, `GET/DELETE /v1/posts/{id}` (cached in Redis)                |
| Post tags | `GET/POST /v1/posts/{id}/tags`, `DELETE /v1/posts/{id}/tags/{tagId}`               |
| Tags      | `GET/POST /v1/tags`, `GET/DELETE /v1/tags/{id}`                                    |

Useful URLs:

- Swagger UI: http://localhost:8080/api/swagger-ui/index.html
- H2 console: http://localhost:8080/api/h2-console
- Health: http://localhost:8080/api/actuator/health

## Build & Test

The `contextLoads` test starts the full application context, so it needs a Redis instance
reachable with the configured password:

```shell
docker run -d --name redis -p 6379:6379 redis:7-alpine \
  redis-server --requirepass eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81
./mvnw clean package
```

Use `SPRING_DATA_REDIS_HOST`, `SPRING_DATA_REDIS_PORT` and `SPRING_DATA_REDIS_PASSWORD` to point to a different Redis.

## CI/CD

[GitHub Actions](.github/workflows/maven.yml) builds and tests the project on every push and pull request to `main`:

- Sets up Temurin JDK 25 with Maven cache
- Starts a password-protected Redis container for the tests
- Runs `mvn -B package`
- Submits the Maven dependency graph (push to `main` only) to improve Dependabot alerts

## Dependabot

[Dependabot](.github/dependabot.yml) checks for updates every Monday at 05:00 (Asia/Jakarta):

| Ecosystem        | What it updates                     | Grouping                                                                                 |
|------------------|-------------------------------------|------------------------------------------------------------------------------------------|
| `maven`          | `pom.xml` dependencies               | `spring-boot`, `springdoc`, `redis-rate-limiting`, `netty`, other minor/patch updates   |
| `github-actions` | Actions in `.github/workflows`       | all actions in a single PR                                                               |
| `docker-compose` | Keycloak & Redis images in `compose.yaml` | one PR per image                                                                    |

PRs are labeled (`dependencies`, `java`, `github-actions`, `docker`) and use conventional commit prefixes
(`chore(deps)`, `ci(deps)`, `chore(docker)`).

## Recommended IDE Plugins

- Google Java Format
- SonarLint
- CheckStyle-IDEA
- GitHub Copilot
- Disable import `*` (Settings → Editor → Code Style → Java → Imports)
