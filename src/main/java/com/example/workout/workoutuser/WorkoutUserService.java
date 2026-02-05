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

    Optional<WorkoutUserDto> findById(Long id) {
        return repository.findById(id).map(this :: convertToDto);
    }

    Optional<WorkoutUser> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    Optional<WorkoutUserDto> findDtoByUsername(String username) {
        return findByUsername(username).map(this :: convertToDto);
    }

    void createWorkoutUser(WorkoutUserPlain workoutUserPlain) {
        WorkoutUser workoutUser = new WorkoutUser(
            workoutUserPlain.email(),
            workoutUserPlain.username(),
            passwordEncoder.encode(workoutUserPlain.plainPassword())
        );
        repository.save(workoutUser);
    }

    void update(String username, WorkoutUser updatedWorkoutUser) {
        WorkoutUser workoutUser = repository.findByUsername(username).get();
        workoutUser.setEmail(updatedWorkoutUser.getEmail());
        // workoutUser.setUsername(updatedWorkoutUser.getUsername()); To be implemented
        String updatedPassword = updatedWorkoutUser.getPassword();
        if (!updatedPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(updatedPassword);
            workoutUser.setPassword(encodedPassword);
        }
        repository.save(workoutUser);
    }

    WorkoutUserDto convertToDto(WorkoutUser workoutUser) {
        return new WorkoutUserDto(
            workoutUser.getId(),
            workoutUser.getEmail(),
            workoutUser.getUsername(),
            workoutUser.getLegExercises()
                        .stream()
                        .map(legExercise -> legExerciseService.convertToDto(legExercise))
                        .toList()
        );
    }
}
