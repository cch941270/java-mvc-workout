package com.example.workout.legexercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import jakarta.validation.ConstraintViolationException;

import com.example.workout.workoutuser.WorkoutUser;
import com.example.workout.workoutuser.WorkoutUserRepository;

@DataJpaTest
@ActiveProfiles("test")
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LegExerciseRepositoryTests {

    private LegExercise legExercise1;
    private LegExercise legExercise2;
    private WorkoutUser workoutUser;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");
    
    @Autowired
    WorkoutUserRepository workoutUserRepository;

    @Autowired
    LegExerciseRepository repository;

    @BeforeEach
    public void setUp() {
        workoutUser = new WorkoutUser(
            "JxGw8@example.com",
            "JxGw8",
            "password"
        );
        workoutUserRepository.save(workoutUser);

        legExercise1 = new LegExercise(
            LegExerciseType.LUNGE,
            LocalDateTime.now(),
            10,
            workoutUser
        );
        repository.save(legExercise1);

        legExercise2 = new LegExercise(
            LegExerciseType.SQUAT,
            LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
            10,
            workoutUser
        );
        repository.save(legExercise2);
    }

    @Test
    @Order(1)
    void shouldFindAll() {
        List<LegExercise> legExercises = repository.findAll();
        assertEquals(435, legExercises.size());
    }

    @Test
    @Order(2)
    void shouldFindById() {
        LegExercise legExercise = repository.findById(legExercise1.getId()).get();
        assertEquals(LegExerciseType.LUNGE, legExercise.getLegExerciseType());
        assertEquals(10, legExercise.getCount());
    }

    @Test
    @Order(3)
    void shouldNotFindByInvalidId() {
        var legExercise = repository.findById(legExercise2.getId() + 1);
        assertEquals(legExercise, Optional.empty());
    }

    @Test
    @Order(4)
    void shouldCreate() {
        repository.save(new LegExercise(
            LegExerciseType.STEP_UP,
            LocalDateTime.now().plus(2, ChronoUnit.HOURS),
            40,
            workoutUser
        ));
        List<LegExercise> legExercises = repository.findAll();
        assertEquals(436, legExercises.size());
    }

    @Test
    @Order(5)
    void shouldNotCreateWithNegativeCount() {
        LegExercise invalidLegExercise = new LegExercise(
            LegExerciseType.STEP_UP,
            LocalDateTime.now().plus(2, ChronoUnit.HOURS),
            -40,
            workoutUser
        );
        assertThrows(ConstraintViolationException.class, () -> {
            repository.saveAndFlush(invalidLegExercise);
        });
    }

    @Test
    @Order(6)
    void shouldUpdate() {
        legExercise1.setLegExerciseType(LegExerciseType.STEP_UP);
        legExercise1.setCount(20);
        repository.save(legExercise1);
        LegExercise updatedLegExercise = repository.findById(legExercise1.getId()).get();
        assertEquals(LegExerciseType.STEP_UP, updatedLegExercise.getLegExerciseType());
        assertEquals(20, updatedLegExercise.getCount());
    }

    @Test
    @Order(7)
    void shouldDelete() {
        repository.delete(legExercise2);
        List<LegExercise> legExercises = repository.findAll();
        assertEquals(434, legExercises.size());
    }
}
