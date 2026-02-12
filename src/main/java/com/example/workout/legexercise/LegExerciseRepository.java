package com.example.workout.legexercise;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface LegExerciseRepository extends JpaRepository<LegExercise, Integer> {

    Optional<LegExercise> findByIdAndWorkoutUserId(Integer id, Long workoutUserId);

    @EntityGraph(attributePaths = {"workoutUser"})
    Page<LegExercise> findAll(Pageable pageable);

    Integer countIdBy();

    Integer countIdByLegExerciseType(LegExerciseType legExerciseType);

    @Query("SELECT SUM(l.count) FROM LegExercise l WHERE l.legExerciseType = :legExerciseType")
    Integer sumCountByLegExerciseType(@Param("legExerciseType") LegExerciseType legExerciseType);
}
