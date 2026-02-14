package com.example.workout.legexercise;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.workout.workoutuser.WorkoutUser;
import com.example.workout.workoutuser.WorkoutUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LegExerciseApiService {
    private final LegExerciseService service;
    private final LegExerciseRepository repository;
    private final WorkoutUserRepository workoutUserRepository;

    List<LegExerciseDto> findAll() {
        return repository.findAll().stream().map(l -> service.convertToDto(l)).toList();
    }

    Optional<LegExerciseDto> findById(Integer id) {
        return repository.findById(id).map(l -> service.convertToDto(l));
    }

    void saveLegExercise(LegExerciseDto legExerciseDto, Integer id) {
        Optional<WorkoutUser> workoutUser = workoutUserRepository.findByEmail(legExerciseDto.workoutUserEmail());
        if (workoutUser.isEmpty()) {
            throw new LegExerciseNotFound();
        }
        LegExercise newLegExercise = new LegExercise(
            id, legExerciseDto.legExerciseType(), legExerciseDto.startedOn(), legExerciseDto.count(), workoutUser.get());
        repository.save(newLegExercise);
    }

    void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
