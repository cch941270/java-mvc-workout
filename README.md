Start: `./mvnw spring-boot:run` <br>
Test: `./mvnw test`

Available endpoints:
| Action | Method | URL|
|---|---|---|
| Create | POST | http://localhost:8080/api/leg-exercises |
| Read | GET | http://localhost:8080/api/leg-exercises |
| Read | GET | http://localhost:8080/api/leg-exercises/{id} |
| Update | PUT | http://localhost:8080/api/leg-exercises/{id} |
| Delete | DELETE | http://localhost:8080/api/leg-exercises/{id} |
| Create | POST | http://localhost:8080/api/workout-users |
| Read | GET | http://localhost:8080/api/workout-users/{id} |
| Update | PUT | http://localhost:8080/api/workout-users/{id} |
| Delete | DELETE | http://localhost:8080/api/workout-users/{id} |

Dependency
- Spring Data JPA for repository
- Validation
- Docker Compose Support for running postgreSQL database in docker
- Flyway migration for database migration
- Spring security
- Testcontainers
