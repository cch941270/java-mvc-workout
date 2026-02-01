package com.example.workout.legexercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import jakarta.validation.ConstraintViolationException;

@DataJpaTest
@ActiveProfiles("test")
@Testcontainers
public class LegExerciseRepositoryTests {

    private LegExercise legExercise1;
    private LegExercise legExercise2;

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
    }

    @Test
    void shouldFindAll() {
        List<LegExercise> legExercises = repository.findAll();
        assertEquals(2, legExercises.size());
    }

    @Test
    void shouldFindById() {
        LegExercise legExercise = repository.findById(legExercise1.getId()).get();
        assertEquals(LegExerciseType.LUNGE, legExercise.getLegExerciseType());
        assertEquals(10, legExercise.getCount());
    }

    @Test
    void shouldNotFindByInvalidId() {
        var legExercise = repository.findById(legExercise2.getId() + 1);
        assertEquals(legExercise, Optional.empty());
    }

    @Test
    void shouldCreate() {
        repository.save(new LegExercise(
            LegExerciseType.STEP_UP,
            LocalDateTime.now().plus(2, ChronoUnit.HOURS),
            40
        ));
        List<LegExercise> legExercises = repository.findAll();
        assertEquals(3, legExercises.size());
    }

    @Test
    void shouldNotCreateWithNegativeCount() {
        LegExercise invalidLegExercise = new LegExercise(
            LegExerciseType.STEP_UP,
            LocalDateTime.now().plus(2, ChronoUnit.HOURS),
            -40
        );
        assertThrows(ConstraintViolationException.class, () -> {
            repository.saveAndFlush(invalidLegExercise);
        });
    }

    @Test
    void shouldUpdate() {
        legExercise1.setLegExerciseType(LegExerciseType.STEP_UP);
        legExercise1.setCount(20);
        repository.save(legExercise1);
        LegExercise updatedLegExercise = repository.findById(legExercise1.getId()).get();
        assertEquals(LegExerciseType.STEP_UP, updatedLegExercise.getLegExerciseType());
        assertEquals(20, updatedLegExercise.getCount());
    }

    @Test
    void shouldDelete() {
        repository.delete(legExercise2);
        List<LegExercise> legExercises = repository.findAll();
        assertEquals(1, legExercises.size());
    }
}
