package com.example.workout.legexercise;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.workout.workoutuser.WorkoutUser;
import com.example.workout.workoutuser.WorkoutUserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/leg-exercises")
public class LegExerciseController {
    private final LegExerciseRepository legExerciseRepository;
    private final LegExerciseService legExerciseService;
    private final WorkoutUserRepository workoutUserRepository;

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<LegExercise> legExercises = legExerciseRepository.findAll();
        model.addAttribute("legExercises", legExercises);
        return "legexercises/index";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String show(@PathVariable Integer id, Model model) {
        Optional<LegExercise> legExercise = legExerciseRepository.findById(id);
        if (legExercise.isEmpty()) {
            return "redirect:/leg-exercises";
        }
        model.addAttribute("legExercise", legExercise.get());
        return "legexercises/show";
    }

    @GetMapping({"/new", "/new/"})
    public String newForm(Model model) {
        model.addAttribute("legExercise", new LegExercise());
        model.addAttribute("allLegExerciseTypes", LegExerciseType.values());
        return "legexercises/new";
    }

    @PostMapping({"", "/"})
    public String create(@ModelAttribute LegExercise legExercise, RedirectAttributes redirectAttributes) {
        WorkoutUser workoutUser = workoutUserRepository.findById((long)1).get();
        legExercise.setWorkoutUser(workoutUser);
        legExerciseRepository.save(legExercise);
        redirectAttributes.addFlashAttribute("success", "Leg exercise created successfully!");
        return "redirect:/leg-exercises";
    }

    @GetMapping({"/{id}/edit", "/{id}/edit/"})
    public String editForm(@PathVariable Integer id, Model model) {
        Optional<LegExercise> legExercise = legExerciseRepository.findById(id);
        if (legExercise.isEmpty()) {
            return "redirect:/leg-exercises";
        }
        model.addAttribute("legExercise", legExercise.get());
        return "legexercises/edit";
    }

    @PutMapping({"/{id}", "/{id}/"})
    public String update(@PathVariable Integer id, @ModelAttribute LegExercise legExercise, RedirectAttributes redirectAttributes) {
        Optional<LegExercise> existingExercise = legExerciseRepository.findById(id);
        if (existingExercise.isEmpty()) {
            return "redirect:/leg-exercises";
        }
        LegExercise exerciseToUpdate = existingExercise.get();
        exerciseToUpdate.setLegExerciseType(legExercise.getLegExerciseType());
        exerciseToUpdate.setStartedOn(legExercise.getStartedOn());
        exerciseToUpdate.setCount(legExercise.getCount());
        legExerciseRepository.save(exerciseToUpdate);
        redirectAttributes.addFlashAttribute("success", "Leg exercise updated successfully!");
        return "redirect:/leg-exercises/{id}";
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public String destroy(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        legExerciseRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Leg exercise deleted successfully!");
        return "redirect:/leg-exercises";
    }
}