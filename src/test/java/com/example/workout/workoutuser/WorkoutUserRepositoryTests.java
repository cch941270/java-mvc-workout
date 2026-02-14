package com.example.workout.workoutuser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.workout.role.Role;
import com.example.workout.role.RoleRepository;
import com.example.workout.role.RoleType;

@DataJpaTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkoutUserRepositoryTests {

    private WorkoutUser workoutUser1;
    private WorkoutUser workoutUser2;
    private Role adminRole;
    private Role userRole;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @Autowired
    WorkoutUserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        adminRole = roleRepository.findByName(RoleType.ADMIN).get();
        userRole = roleRepository.findByName(RoleType.USER).get();

        workoutUser1 = new WorkoutUser(
            null,
            "admin@example.com",
            "adminuser",
            "adminpassword",
            Set.of(adminRole)
        );
        repository.save(workoutUser1);

        workoutUser2 = new WorkoutUser(
            null,
            "user@example.com",
            "normaluser",
            "userpassword",
            Set.of(userRole)
        );
        repository.save(workoutUser2);
    }

    @Test
    @Order(1)
    void shouldFindByEmail() {
        Optional<WorkoutUser> user = repository.findByEmail("admin@example.com");
        assertTrue(user.isPresent());
        assertEquals("adminuser", user.get().getUsername());
    }

    @Test
    @Order(2)
    void shouldNotFindByInvalidEmail() {
        Optional<WorkoutUser> user = repository.findByEmail("invalid@example.com");
        assertFalse(user.isPresent());
    }

    @Test
    @Order(3)
    void shouldFindByUsername() {
        Optional<WorkoutUser> user = repository.findByUsername("normaluser");
        assertTrue(user.isPresent());
        assertEquals("user@example.com", user.get().getEmail());
    }

    @Test
    @Order(4)
    void shouldNotFindByInvalidUsername() {
        Optional<WorkoutUser> user = repository.findByUsername("invaliduser");
        assertFalse(user.isPresent());
    }

    @Test
    @Order(5)
    void shouldFindByRolesNameWithPagination() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<WorkoutUser> page = repository.findByRoles_Name(RoleType.ADMIN, pageable);
        assertEquals(2, page.getContent().size());
        assertEquals("adminuser", page.getContent().get(1).getUsername());
    }

    @Test
    @Order(6)
    void shouldFindByEmailContainingAndRolesNameWithPagination() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<WorkoutUser> page = repository.findByEmailContainingAndRoles_Name("user", RoleType.USER, pageable);
        assertEquals(3, page.getContent().size());
        assertEquals("normaluser", page.getContent().get(2).getUsername());
    }

    @Test
    @Order(7)
    void shouldCountByRolesName() {
        Integer count = repository.countByRoles_Name(RoleType.ADMIN);
        assertEquals(2, count);
    }
}