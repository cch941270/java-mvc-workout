package com.example.workout.legexercise;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.workout.workoutuser.WorkoutUser;
import com.example.workout.workoutuser.WorkoutUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LegExerciseService {
    private final LegExerciseRepository repository;
    private final WorkoutUserRepository workoutUserRepository;

    public List<LegExercise> findAllSortedByStartedOn() {
        Sort sortByStartedOnDesc = Sort.by(Sort.Direction.DESC, "startedOn");
        return repository.findAll(sortByStartedOnDesc);
    }

    List<LegExercise> findAllByUsernameSorted(String username) {
        List<LegExercise> legExercises = workoutUserRepository.findByUsername(username).get().getLegExercises();
        legExercises.sort(Comparator.comparing(LegExercise::getStartedOn).reversed());
        return legExercises;
    }

    public Page<LegExercise> findPaginatedAndSorted(int pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "startedOn");
        Pageable pageable = PageRequest.of(pageNo - 1, 9, sort);
        return repository.findAll(pageable);
    }

    Optional<LegExercise> findByUsernameAndId(String username, Integer id) {
        WorkoutUser workoutUser = workoutUserRepository.findByUsername(username).get();
        return repository.findByIdAndWorkoutUserId(id, workoutUser.getId());
    }

    void create(String username, LegExercise legExercise) {
        WorkoutUser workoutUser = workoutUserRepository.findByUsername(username).get();
        legExercise.setWorkoutUser(workoutUser);
        repository.save(legExercise);
    }

    void update(LegExercise legExercise, LegExercise updatedLegExercise) {
        legExercise.setLegExerciseType(updatedLegExercise.getLegExerciseType());
        legExercise.setStartedOn(updatedLegExercise.getStartedOn());
        legExercise.setCount(updatedLegExercise.getCount());
        repository.save(legExercise);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
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
