Start: `./mvnw spring-boot:run` <br>
Test: `./mvnw test`
<hr>
User entry point: http://localhost:8080/ <br>
Admin entry point: http://localhost:8080/admin/

Sample admin:
```
username: admin
password: password
```
Sample user1:
```
username: user1
password: password1
```
Sample user2:
```
username: user2
password: password2
```
<hr>

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

<hr>

Dependency
- Spring Data JPA
- Validation
- Docker Compose Support for running postgreSQL database in docker
- Spring security
- Thymeleaf
- Flyway
- Testcontainers
