package com.example.workout.legexercise;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class LegExerciseControllerIntTests {

    @LocalServerPort
    int randomServerPort;

    RestClient client;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @BeforeEach
    void setUp() {
        client = RestClient.create("http://localhost:" + randomServerPort);
    }

    @Test
    void shouldFindAll() {
        List<LegExercise> legExercises = client.get()
                                            .uri("/api/leg-exercises")
                                            .retrieve()
                                            .body(new ParameterizedTypeReference<>() {});
        assertEquals(legExercises.size(), 6);
    }

    @Test
    void shouldFindById() {
        LegExercise legExercise = client.get()
                                    .uri("/api/leg-exercises/1")
                                    .retrieve()
                                    .body(LegExercise.class);
        assertAll(
            () -> assertEquals(1, (int)legExercise.getId()),
            () -> assertEquals(LegExerciseType.STEP_UP, legExercise.getLegExerciseType()),
            () -> assertEquals(LocalDateTime.parse("2026-01-20T18:00:00"), legExercise.getStartedOn()),
            () -> assertEquals(40, (int)legExercise.getCount())
        );
    }

    @Test
    void shouldCreate() {
        LegExercise legExercise = new LegExercise(LegExerciseType.STEP_UP, LocalDateTime.now(), 40);

        ResponseEntity<Void> responseEntity = client.post()
                                                .uri("/api/leg-exercises")
                                                .body(legExercise)
                                                .retrieve()
                                                .toBodilessEntity();
        assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Test
    void shouldUpdate() {
        LegExercise legExercise = client.get()
                                            .uri("/api/leg-exercises/1")
                                            .retrieve()
                                            .body(LegExercise.class);
        legExercise.setCount(60);

        ResponseEntity<Void> responseEntity = client.put()
                                                .uri("/api/leg-exercises/1")
                                                .body(legExercise)
                                                .retrieve()
                                                .toBodilessEntity();
        assertEquals(204, responseEntity.getStatusCode().value());
    }

    @Test
    void shouldDelete() {
        ResponseEntity<Void> responseEntity = client.delete()
                                                .uri("/api/leg-exercises/2")
                                                .retrieve()
                                                .toBodilessEntity();
        assertEquals(204, responseEntity.getStatusCode().value());
    }
}
