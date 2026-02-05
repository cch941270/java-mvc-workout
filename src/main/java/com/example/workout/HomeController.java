package com.example.workout;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.workout.legexercise.LegExercise;
import com.example.workout.legexercise.LegExerciseService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LegExerciseService legExerciseService;

    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            return "redirect:/leg-exercises";
        }
        List<LegExercise> legExercises = legExerciseService.findAllSortedByStartedOn();
        model.addAttribute("legExercises", legExercises);
        return "home/index";
    }

    @GetMapping("/login")
    public String customLogin() {
        return "login";
    }

}
