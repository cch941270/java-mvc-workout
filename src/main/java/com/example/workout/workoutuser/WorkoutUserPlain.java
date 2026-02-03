package com.example.workout.workoutuser;

public record WorkoutUserPlain(
    String email,
    String plainPassword
) {
    public WorkoutUserPlain() {
        this("", "");
    }
}
