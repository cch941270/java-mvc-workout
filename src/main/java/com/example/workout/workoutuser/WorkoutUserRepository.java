package com.example.workout.workoutuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutUserRepository extends JpaRepository<WorkoutUser, Long> {
    Optional<WorkoutUser> findByUsername(String username);
}
