package com.example.workout.workoutuser;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final UserDetailsService userDetailsService;

    Optional<WorkoutUserDto> findById(Long id) {
        return repository.findById(id).map(this :: convertToDto);
    }

    Optional<WorkoutUser> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    Optional<WorkoutUserDto> findDtoByUsername(String username) {
        return findByUsername(username).map(this :: convertToDto);
    }

    void create(WorkoutUserPlain workoutUserPlain) {
        WorkoutUser workoutUser = new WorkoutUser(
            workoutUserPlain.email(),
            workoutUserPlain.username(),
            passwordEncoder.encode(workoutUserPlain.plainPassword())
        );
        repository.save(workoutUser);
    }

    void update(String username, WorkoutUser updatedWorkoutUser) {
        WorkoutUser workoutUser = repository.findByUsername(username).get();
        String updatedUsername = updatedWorkoutUser.getUsername();
        String updatedPassword = updatedWorkoutUser.getPassword();
        workoutUser.setEmail(updatedWorkoutUser.getEmail());
        workoutUser.setUsername(updatedUsername);
        if (!updatedPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(updatedPassword);
            workoutUser.setPassword(encodedPassword);
        }
        repository.save(workoutUser);
        if (username != updatedUsername) {
            refreshAuthentication(updatedUsername);
        }
    }

    void delete(String username) {
        WorkoutUser workoutUser = repository.findByUsername(username).get();
        repository.delete(workoutUser);
        SecurityContextHolder.getContext().setAuthentication(null);
    }


    void refreshAuthentication(String newUsername) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(newUsername);

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
            userDetails,
            userDetails.getPassword(),
            userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
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
