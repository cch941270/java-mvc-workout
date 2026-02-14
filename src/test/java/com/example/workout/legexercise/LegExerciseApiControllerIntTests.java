package com.example.workout.legexercise;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LegExerciseApiControllerIntTests {

    @Autowired
    WebApplicationContext context;

    WebTestClient client;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @BeforeEach
    void setUp() {
        client = MockMvcWebTestClient
                    .bindToApplicationContext(context)
                    .apply(springSecurity())
                    .defaultRequest(get("/").with(csrf()))
                    .configureClient()
                    .build();
    }

    @Test
    @Order(1)
    void shouldFindAll() {
        client.get()
                .uri("/api/leg-exercises")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(LegExerciseDto.class)
                .hasSize(433);
    }

    @Test
    @Order(2)
    void shouldFindById() {
        client.get()
                .uri("/api/leg-exercises/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.legExerciseType").isEqualTo(LegExerciseType.SQUAT)
                .jsonPath("$.startedOn").isEqualTo(LocalDateTime.parse("2026-01-17T10:00:00"))
                .jsonPath("$.count").isEqualTo(10);
    }

    @Test
    @Order(3)
    void shouldCreate() {
        LegExerciseDto legExerciseDto = new LegExerciseDto(
            null,
            LegExerciseType.STEP_UP,
            LocalDateTime.now(),
            40,
            "user1@example.com"
        );

        client.post()
                .uri("/api/leg-exercises")
                .bodyValue(legExerciseDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    @Test
    @Order(4)
    void shouldUpdate() {
        LegExerciseDto legExerciseDto = client.get()
                                            .uri("/api/leg-exercises/1")
                                            .exchange()
                                            .expectBody(LegExerciseDto.class)
                                            .returnResult()
                                            .getResponseBody();

        LegExerciseDto updatedLegExerciseDto = new LegExerciseDto(
            legExerciseDto.id(),
            legExerciseDto.legExerciseType(),
            legExerciseDto.startedOn(),
            60,
            legExerciseDto.workoutUserEmail()
        );

        client.put()
                .uri("/api/leg-exercises/1")
                .bodyValue(updatedLegExerciseDto)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
    }

    @Test
    @Order(5)
    void shouldDelete() {
        client.delete()
                .uri("/api/leg-exercises/2")
                .exchange()
                .expectStatus().isNoContent();
    }
}
