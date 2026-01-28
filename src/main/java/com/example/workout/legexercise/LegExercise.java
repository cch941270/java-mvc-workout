package com.example.workout.legexercise;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.Positive;

public record LegExercise(
    @Id
    Integer id,
    LegExerciseType leg_exercise_type,
    LocalDateTime startedOn,
    @Positive
    Integer count,
    @Version
    Integer version
) {}
