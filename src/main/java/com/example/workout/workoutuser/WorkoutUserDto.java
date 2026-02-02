package com.example.workout.workoutuser;

import java.util.List;

import com.example.workout.legexercise.LegExerciseDto;

public record WorkoutUserDto(
    Long id,
    String email,
    List<LegExerciseDto> legExerciseDtos
) {}
