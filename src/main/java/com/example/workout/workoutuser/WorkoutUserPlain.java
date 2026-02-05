package com.example.workout.workoutuser;

public record WorkoutUserPlain(
    String email,
    String username,
    String plainPassword
) {
    public WorkoutUserPlain() {
        this("", "", "");
    }
}
