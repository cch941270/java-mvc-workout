package com.example.workout.legexercise;

import java.time.LocalDateTime;

public record LegExerciseDto(
    Integer id,
    LegExerciseType legExerciseType,
    LocalDateTime startedOn,
    Integer count,
    String workoutUserEmail
) {}
