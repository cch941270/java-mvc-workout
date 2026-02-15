package com.example.workout.legexercise;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.example.workout.workoutuser.WorkoutUser;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "leg_exercises")
public class LegExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leg_exercises_id_seq")
    @SequenceGenerator(name = "leg_exercises_id_seq", sequenceName = "leg_exercises_id_seq", allocationSize = 1)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private LegExerciseType legExerciseType;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startedOn;
    @Positive
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_user_id")
    private WorkoutUser workoutUser;

    public LegExercise(LegExerciseType legExerciseType, LocalDateTime startedOn, Integer count, WorkoutUser workoutUser) {
        super();
        this.legExerciseType = legExerciseType;
        this.startedOn = startedOn;
        this.count = count;
        this.workoutUser = workoutUser;
    }

    public void setLegExerciseType(LegExerciseType legExerciseType) {
        this.legExerciseType = legExerciseType;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setWorkoutUser(WorkoutUser workoutUser) {
        this.workoutUser = workoutUser;
    }
}
