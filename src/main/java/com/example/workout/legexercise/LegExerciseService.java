package com.example.workout.legexercise;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LegExerciseService {
    private final LegExerciseRepository repository;

    public List<LegExerciseDto> findAll() {
        return repository.findAll().stream().map(this :: convertToDto).toList();
    }

    public Optional<LegExerciseDto> findById(Integer id) {
        return repository.findById(id).map(this :: convertToDto);
    }

    public LegExerciseDto convertToDto(LegExercise legExercise) {
        return new LegExerciseDto(
            legExercise.getId(),
            legExercise.getLegExerciseType(),
            legExercise.getStartedOn(),
            legExercise.getCount(),
            legExercise.getWorkoutUser().getEmail()
        );
    }
}
