package com.example.workout.legexercise;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    void shouldFindAll() {
        List<LegExerciseDto> legExerciseDtos = client.get()
                                            .uri("/api/leg-exercises")
                                            .retrieve()
                                            .body(new ParameterizedTypeReference<>() {});
        assertEquals(13, legExerciseDtos.size());
    }

    @Test
    void shouldFindById() {
        LegExerciseDto legExerciseDto = client.get()
                                    .uri("/api/leg-exercises/1")
                                    .retrieve()
                                    .body(LegExerciseDto.class);
        assertAll(
            () -> assertEquals(1, (int)legExerciseDto.id()),
            () -> assertEquals(LegExerciseType.SQUAT, legExerciseDto.legExerciseType()),
            () -> assertEquals(LocalDateTime.parse("2026-01-17T10:00:00"), legExerciseDto.startedOn()),
            () -> assertEquals(10, (int)legExerciseDto.count())
        );
    }

    // @Test
    // void shouldCreate() {
    //     LegExercise legExercise = new LegExercise(LegExerciseType.STEP_UP, LocalDateTime.now(), 40);

    //     ResponseEntity<Void> responseEntity = client.post()
    //                                             .uri("/api/leg-exercises")
    //                                             .body(legExercise)
    //                                             .retrieve()
    //                                             .toBodilessEntity();
    //     assertEquals(201, responseEntity.getStatusCode().value());
    // }

    // @Test
    // void shouldUpdate() {
    //     LegExercise legExercise = client.get()
    //                                         .uri("/api/leg-exercises/1")
    //                                         .retrieve()
    //                                         .body(LegExercise.class);
    //     legExercise.setCount(60);

    //     ResponseEntity<Void> responseEntity = client.put()
    //                                             .uri("/api/leg-exercises/1")
    //                                             .body(legExercise)
    //                                             .retrieve()
    //                                             .toBodilessEntity();
    //     assertEquals(204, responseEntity.getStatusCode().value());
    // }

    @Test
    void shouldDelete() {
        ResponseEntity<Void> responseEntity = client.delete()
                                                .uri("/api/leg-exercises/2")
                                                .retrieve()
                                                .toBodilessEntity();
        assertEquals(204, responseEntity.getStatusCode().value());
    }
}
