package com.example.workout.workoutuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.example.workout.legexercise.LegExercise;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workout_users")
public class WorkoutUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_users_id_seq")
    @SequenceGenerator(name = "workout_users_id_seq", sequenceName = "workout_users_id_seq", allocationSize = 1)
    private Long id;
    private String email;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @OneToMany(mappedBy = "workoutUser", cascade = CascadeType.ALL)
    private List<LegExercise> legExercises;

    public WorkoutUser(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public WorkoutUser(Long id, String email, String hashedPassword) {
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }
}
