package com.example.workout.legexercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@DataJpaTest
public class LegExerciseRepositoryTests {

    private LegExercise legExercise1;
    private LegExercise legExercise2;
    private LegExercise legExercise3;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");
    
    @Autowired
    LegExerciseRepository repository;

    @BeforeEach
    public void setUp() {
        legExercise1 = new LegExercise(
            LegExerciseType.LUNGE,
            LocalDateTime.now(),
            10
        );
        repository.save(legExercise1);

        legExercise2 = new LegExercise(
            LegExerciseType.SQUAT,
            LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
            10
        );
        repository.save(legExercise2);

        legExercise3 = new LegExercise(
            LegExerciseType.STEP_UP,
            LocalDateTime.now().plus(1, ChronoUnit.HOURS),
            40
        );
        repository.save(legExercise3);
    }

    @AfterEach
    public void tearDown() {
        repository.delete(legExercise1);
        repository.delete(legExercise2);
        repository.delete(legExercise3);
    }

    @Test
    void shouldFindAllLegExercises() {
        List<LegExercise> legExercises = repository.findAll();
        assertEquals(3, legExercises.size());
    }
}
