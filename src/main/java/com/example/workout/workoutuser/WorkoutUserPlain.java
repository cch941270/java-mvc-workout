package com.example.workout.workoutuser;

public record WorkoutUserPlain(
    String email,
    String username,
    String plainPassword,
    String confirmPassword
) {
    public WorkoutUserPlain() {
        this("", "", "", "");
    }

    public WorkoutUserPlain(String email, String username) {
        this(email, username, "", "");
    }
}
