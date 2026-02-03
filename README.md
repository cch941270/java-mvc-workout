Start: `./mvnw spring-boot:run` <br>
Test: `./mvnw test`

Available pages:
- [/leg-exercises](http://localhost:8080/leg-exercises)
- [/leg-exercises/new](http://localhost:8080/leg-exercises/new)
- http://localhost:8080/leg-exercises/{id}/edit
- http://localhost:8080/leg-exercises/{id}
- [/users/new](http://localhost:8080/users/new)
- http://localhost:8080/users/{id}/edit
- http://localhost:8080/users/{id}

Available API endpoints:
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
- Spring security for password encryption
- Testcontainers
