package com.example.workout.admin;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import com.example.workout.legexercise.LegExercise;
import com.example.workout.legexercise.LegExerciseRepository;
import com.example.workout.legexercise.LegExerciseType;
import com.example.workout.role.RoleType;
import com.example.workout.workoutuser.WorkoutUser;
import com.example.workout.workoutuser.WorkoutUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final LegExerciseRepository legExerciseRepository;
    private final WorkoutUserRepository workoutUserRepository;
    private final SessionRegistry sessionRegistry;
    private final FindByIndexNameSessionRepository<? extends Session> sessions;

    @Cacheable(value = "numberOfUsers")
    Integer numberOfUsers() {
        return workoutUserRepository.countByRoles_Name(RoleType.USER);
    }

    @Cacheable(value = "numberOfLegExercises")
    Integer numberOfLegExercises() {
        return legExerciseRepository.countIdBy();
    }

    @Cacheable(value = "numberOfLegExercisesByType")
    Integer numberOfLegExercisesByType(LegExerciseType type) {
        return legExerciseRepository.countIdByLegExerciseType(type);
    }

    @Cacheable(value = "numberOfLegExerciseCountsByType")
    Integer numberOfLegExerciseCountsByType(LegExerciseType type) {
        return legExerciseRepository.sumCountByLegExerciseType(type);
    }

    Page<LegExercise> findPaginatedAndSorted(int pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "startedOn");
        Pageable pageable = PageRequest.of(pageNo - 1, 9, sort);
        return legExerciseRepository.findAll(pageable);
    }

    Page<WorkoutUser> findPaginatedAndSorted(int pageNo, Optional<String> email, Optional<String> sortBy) {
        String sortByOrElse = sortBy.orElse("id_asc");
        String[] sortAndOrder = sortByOrElse.split("_");
        Direction direction = sortAndOrder[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortAndOrder[0]);
        Pageable pageable = PageRequest.of(pageNo - 1, 10, sort);
        RoleType roleType = RoleType.USER;
        if (email.isEmpty()) {
            return workoutUserRepository.findByRoles_Name(roleType, pageable);
        } else {
            return workoutUserRepository.findByEmailContainingAndRoles_Name(email.get(), roleType, pageable);
        }
    }

    List<WorkoutUser> findAllOnline() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        List<Object> onlinePrincipals = allPrincipals.stream().filter(principal -> {
            List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
            return !sessions.isEmpty();
        }).collect(Collectors.toList());

        return onlinePrincipals.stream().map(p -> (WorkoutUser) p).toList();
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

    void logout(Long id) {
        WorkoutUser workoutUser = workoutUserRepository.findById(id).get();
        Collection<? extends Session> allUsersessions = getSessions(workoutUser.getUsername());
        for (Session session : allUsersessions) {
            removeSession(workoutUser.getUsername(), session.getId());
        }
    }

    void delete(Long id) {
        logout(id);
        workoutUserRepository.deleteById(id);
    }

    void delete(Integer id) {
        legExerciseRepository.deleteById(id);
    }
}
