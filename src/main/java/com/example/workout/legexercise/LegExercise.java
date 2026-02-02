package com.example.workout.legexercise;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

import com.example.workout.workoutuser.WorkoutUser;

@Entity
@Table(name = "leg_exercises")
public class LegExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leg_exercises_id_seq")
    @SequenceGenerator(name = "leg_exercises_id_seq", sequenceName = "leg_exercises_id_seq", allocationSize = 1)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private LegExerciseType legExerciseType;
    private LocalDateTime startedOn;
    @Positive
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_user_id")
    private WorkoutUser workoutUser;

    public LegExercise() {
        super();
    }

    public LegExercise(LegExerciseType legExerciseType, LocalDateTime startedOn, Integer count, WorkoutUser workoutUser) {
        super();
        this.legExerciseType = legExerciseType;
        this.startedOn = startedOn;
        this.count = count;
        this.workoutUser = workoutUser;
    }

    public LegExercise(Integer id, LegExerciseType legExerciseType, LocalDateTime startedOn, Integer count, WorkoutUser workoutUser) {
        super();
        this.id = id;
        this.legExerciseType = legExerciseType;
        this.startedOn = startedOn;
        this.count = count;
        this.workoutUser = workoutUser;
    }

    public Integer getId() {
        return this.id;
    }

    public LegExerciseType getLegExerciseType() {
        return legExerciseType;
    }

    public void setLegExerciseType(LegExerciseType legExerciseType) {
        this.legExerciseType = legExerciseType;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public WorkoutUser getWorkoutUser() {
        return this.workoutUser;
    }

    public void setWorkoutUser(WorkoutUser workoutUser) {
        this.workoutUser = workoutUser;
    }
}
