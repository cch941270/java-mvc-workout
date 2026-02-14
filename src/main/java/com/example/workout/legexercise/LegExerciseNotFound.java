package com.example.workout.legexercise;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LegExerciseNotFound extends RuntimeException {
    public LegExerciseNotFound() {
        super("Leg Exercise not found.");
    }
}
