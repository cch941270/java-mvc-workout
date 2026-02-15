package com.example.workout.workoutuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkoutUserDetailsService implements UserDetailsService {

    private final WorkoutUserRepository workoutUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return workoutUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
