package com.example.workout.workoutuser;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.workout.role.RoleType;

@Repository
public interface WorkoutUserRepository extends JpaRepository<WorkoutUser, Long> {
    Optional<WorkoutUser> findByUsername(String username);
    Page<WorkoutUser> findByRoles_Name(RoleType roleType, Pageable pageable);
}
