package com.example.workout.workoutuser;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import com.example.workout.legexercise.LegExerciseService;
import com.example.workout.role.Role;
import com.example.workout.role.RoleRepository;
import com.example.workout.role.RoleType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkoutUserService {
    private final WorkoutUserRepository repository;
    private final RoleRepository roleRepository;
    private final LegExerciseService legExerciseService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final SessionRegistry sessionRegistry;
    private final FindByIndexNameSessionRepository<? extends Session> sessions;

    Optional<WorkoutUserDto> findById(Long id) {
        return repository.findById(id).map(this :: convertToDto);
    }

    Optional<WorkoutUser> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    Optional<WorkoutUserDto> findDtoByUsername(String username) {
        return findByUsername(username).map(this :: convertToDto);
    }

    public Page<WorkoutUser> findPaginatedAndSorted(int pageNo, Optional<String> email, Optional<String> sortBy) {
        String sortByOrElse = sortBy.orElse("id_asc");
        String[] sortAndOrder = sortByOrElse.split("_");
        Direction direction = sortAndOrder[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortAndOrder[0]);
        Pageable pageable = PageRequest.of(pageNo - 1, 10, sort);
        RoleType roleType = RoleType.USER;
        if (email.isEmpty()) {
            return repository.findByRoles_Name(roleType, pageable);
        } else {
            return repository.findByEmailContainingAndRoles_Name(email.get(), roleType, pageable);
        }
    }

    public List<WorkoutUser> findAllOnline() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        List<Object> onlinePrincipals = allPrincipals.stream().filter(principal -> {
            List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
            return !sessions.isEmpty();
        }).collect(Collectors.toList());

        return onlinePrincipals.stream().map(p -> (WorkoutUser) p).toList();
    }

    void create(WorkoutUserPlain workoutUserPlain) {
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

    public void logout(Long id) {
        WorkoutUser workoutUser = repository.findById(id).get();
        Collection<? extends Session> allUsersessions = getSessions(workoutUser.getUsername());
        for (Session session : allUsersessions) {
            removeSession(workoutUser.getUsername(), session.getId());
        }
    }

    public void delete(Long id) {
        logout(id);
        repository.deleteById(id);
    }

    Collection<? extends Session> getSessions(String principalName) {
        Collection<? extends Session> userSessions = this.sessions.findByPrincipalName(principalName).values();
        return userSessions;
    }

    void removeSession(String principalName, String sessionIdToDelete) {
        Set<String> userSessionIds = this.sessions.findByPrincipalName(principalName).keySet();
        if (userSessionIds.contains(sessionIdToDelete)) {
            this.sessions.deleteById(sessionIdToDelete);
        }
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
