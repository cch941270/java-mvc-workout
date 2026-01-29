package com.example.workout.legexercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegExerciseRepository extends JpaRepository<LegExercise, Integer> {}
