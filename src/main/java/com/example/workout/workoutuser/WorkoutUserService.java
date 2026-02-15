package com.example.workout.workoutuser;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.workout.legexercise.LegExerciseService;
import com.example.workout.role.Role;
import com.example.workout.role.RoleRepository;
import com.example.workout.role.RoleType;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutUserService {
    private final WorkoutUserRepository repository;
    private final RoleRepository roleRepository;
    private final LegExerciseService legExerciseService;
    private final UserDetailsService userDetailsService;
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

    void create(WorkoutUserPlain workoutUserPlain) {
        if (!workoutUserPlain.plainPassword().equals(workoutUserPlain.confirmPassword())) {
            throw new PasswordsNotTheSame();
        }
        Role userRole = roleRepository.findByName(RoleType.USER).get();
        WorkoutUser workoutUser = new WorkoutUser(
            null,
            workoutUserPlain.email(),
            workoutUserPlain.username(),
            passwordEncoder.encode(workoutUserPlain.plainPassword()),
            Set.of(userRole)
        );
        repository.save(workoutUser);
    }

    void update(String username, WorkoutUserPlain workoutUserPlain, HttpSession session) {
        if (!workoutUserPlain.plainPassword().equals(workoutUserPlain.confirmPassword())) {
            throw new PasswordsNotTheSame();
        }
        WorkoutUser workoutUser = repository.findByUsername(username).get();
        String updatedUsername = workoutUserPlain.username();
        String updatedPassword = workoutUserPlain.plainPassword();
        workoutUser.setEmail(workoutUserPlain.email());
        workoutUser.setUsername(updatedUsername);
        if (!updatedPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(updatedPassword);
            workoutUser.setPassword(encodedPassword);
        }
        repository.save(workoutUser);
        if (username != updatedUsername) {
            refreshAuthentication(updatedUsername, session);
        }
    }

    void delete(String username) {
        WorkoutUser workoutUser = repository.findByUsername(username).get();
        repository.delete(workoutUser);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    void refreshAuthentication(String newUsername, HttpSession session) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(newUsername);
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
    }

    public WorkoutUserDto convertToDto(WorkoutUser workoutUser) {
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
