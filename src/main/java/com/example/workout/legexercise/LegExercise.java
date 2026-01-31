package com.example.workout.legexercise;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "leg_exercise")
public class LegExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leg_exercise_seq")
    @SequenceGenerator(name = "leg_exercise_seq", sequenceName = "leg_exercise_seq", allocationSize = 1)
    private Integer id;
    private LegExerciseType legExerciseType;
    private LocalDateTime startedOn;
    @Positive
    private Integer count;

    public LegExercise() {
        super();
    }

    public LegExercise(LegExerciseType legExerciseType, LocalDateTime startedOn, Integer count) {
        super();
        this.legExerciseType = legExerciseType;
        this.startedOn = startedOn;
        this.count = count;
    }

    public LegExercise(Integer id, LegExerciseType legExerciseType, LocalDateTime startedOn, Integer count) {
        super();
        this.id = id;
        this.legExerciseType = legExerciseType;
        this.startedOn = startedOn;
        this.count = count;
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
}
