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
                .hasSize(13);
    }

    @Test
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
        client.delete()
                .uri("/api/leg-exercises/2")
                .exchange()
                .expectStatus().isNoContent();
    }
}
