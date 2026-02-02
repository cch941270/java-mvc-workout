package com.example.workout.workoutuser;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.workout.legexercise.LegExerciseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutUserService {
    private final WorkoutUserRepository repository;
    private final LegExerciseService legExerciseService;
    private final PasswordEncoder passwordEncoder;

    public Optional<WorkoutUserDto> findById(Long id) {
        return repository.findById(id).map(this :: convertToDto);
    }

    public void createWorkoutUser(WorkoutUserPlain workoutUserPlain) {
        WorkoutUser workoutUser = new WorkoutUser(
            workoutUserPlain.email(),
            passwordEncoder.encode(workoutUserPlain.plainPassword())
        );
        repository.save(workoutUser);
    }

    private WorkoutUserDto convertToDto(WorkoutUser workoutUser) {
        return new WorkoutUserDto(
            workoutUser.getId(),
            workoutUser.getEmail(),
            workoutUser.getLegExercises()
                        .stream()
                        .map(legExercise -> legExerciseService.convertToDto(legExercise))
                        .toList()
        );
    }
}
