package com.example.workout.legexercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import jakarta.validation.ConstraintViolationException;

import com.example.workout.role.Role;
import com.example.workout.role.RoleRepository;
import com.example.workout.role.RoleType;
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
    RoleRepository roleRepository;

    @Autowired
    WorkoutUserRepository workoutUserRepository;

    @Autowired
    LegExerciseRepository repository;

    @BeforeEach
    public void setUp() {
        final Role userRole = roleRepository.findByName(RoleType.USER).get();
        workoutUser = new WorkoutUser(
            null,
            "JxGw8@example.com",
            "JxGw8",
            "password",
            Set.of(userRole)

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
    void shouldFindByIdAndWorkoutUserId() {
        Optional<LegExercise> legExercise = repository.findByIdAndWorkoutUserId(legExercise1.getId(), workoutUser.getId());
        assertTrue(legExercise.isPresent());
        assertEquals(LegExerciseType.LUNGE, legExercise.get().getLegExerciseType());
    }

    @Test
    @Order(3)
    void shouldNotFindByIdAndWorkoutUserIdWithInvalidUserId() {
        Optional<LegExercise> legExercise = repository.findByIdAndWorkoutUserId(legExercise1.getId(), workoutUser.getId() + 1);
        assertTrue(legExercise.isEmpty());
    }

    @Test
    @Order(4)
    void shouldFindAllWithPagination() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<LegExercise> page = repository.findAll(pageable);
        assertEquals(10, page.getContent().size());
        assertEquals(435, page.getTotalElements());
    }

    @Test
    @Order(5)
    void shouldCountAllLegExercises() {
        Integer count = repository.countIdBy();
        assertEquals(435, count);
    }

    @Test
    @Order(6)
    void shouldCountLegExercisesByType() {
        Integer count = repository.countIdByLegExerciseType(LegExerciseType.LUNGE);
        assertTrue(count > 0);
    }

    @Test
    @Order(7)
    void shouldSumCountByLegExerciseType() {
        Integer sum = repository.sumCountByLegExerciseType(LegExerciseType.LUNGE);
        assertTrue(sum > 0);
    }

    @Test
    @Order(8)
    void shouldFindById() {
        LegExercise legExercise = repository.findById(legExercise1.getId()).get();
        assertEquals(LegExerciseType.LUNGE, legExercise.getLegExerciseType());
        assertEquals(10, legExercise.getCount());
    }

    @Test
    @Order(9)
    void shouldNotFindByInvalidId() {
        var legExercise = repository.findById(legExercise2.getId() + 1);
        assertEquals(legExercise, Optional.empty());
    }

    @Test
    @Order(10)
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
    @Order(11)
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
    @Order(12)
    void shouldUpdate() {
        legExercise1.setLegExerciseType(LegExerciseType.STEP_UP);
        legExercise1.setCount(20);
        repository.save(legExercise1);
        LegExercise updatedLegExercise = repository.findById(legExercise1.getId()).get();
        assertEquals(LegExerciseType.STEP_UP, updatedLegExercise.getLegExerciseType());
        assertEquals(20, updatedLegExercise.getCount());
    }

    @Test
    @Order(13)
    void shouldDelete() {
        repository.delete(legExercise2);
        List<LegExercise> legExercises = repository.findAll();
        assertEquals(434, legExercises.size());
    }
}
