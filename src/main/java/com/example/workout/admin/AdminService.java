package com.example.workout.admin;

import org.springframework.stereotype.Service;

import com.example.workout.legexercise.LegExerciseRepository;
import com.example.workout.legexercise.LegExerciseType;
import com.example.workout.role.RoleType;
import com.example.workout.workoutuser.WorkoutUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final LegExerciseRepository legExerciseRepository;
    private final WorkoutUserRepository workoutUserRepository;

    Integer numberOfUsers() {
        return workoutUserRepository.countByRoles_Name(RoleType.USER);
    }

    Integer numberOfLegExercises() {
        return legExerciseRepository.countIdBy();
    }

    Integer numberOfLegExercisesByType(LegExerciseType type) {
        return legExerciseRepository.countIdByLegExerciseType(type);
    }

    Integer numberOfLegExerciseCountsByType(LegExerciseType type) {
        return legExerciseRepository.sumCountByLegExerciseType(type);
    }
}
