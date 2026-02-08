package com.example.workout.role;

import java.util.Set;

import com.example.workout.workoutuser.WorkoutUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RoleType name;

    @ManyToMany(mappedBy = "roles")
    private Set<WorkoutUser> workoutUsers;

    public Role() {
        super();
    }

}
