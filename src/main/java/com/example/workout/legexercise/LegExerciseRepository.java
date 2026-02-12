package com.example.workout.legexercise;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegExerciseRepository extends JpaRepository<LegExercise, Integer> {

    Optional<LegExercise> findByIdAndWorkoutUserId(Integer id, Long workoutUserId);

    @EntityGraph(attributePaths = {"workoutUser"})
    Page<LegExercise> findAll(Pageable pageable);
}
