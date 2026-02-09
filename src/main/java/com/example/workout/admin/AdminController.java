package com.example.workout.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.workout.legexercise.LegExercise;
import com.example.workout.legexercise.LegExerciseDto;
import com.example.workout.legexercise.LegExerciseService;
import com.example.workout.workoutuser.WorkoutUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    
    private final LegExerciseService legExerciseService;
    private final WorkoutUserService workoutUserService;


    @GetMapping({"leg-exercises", "leg-exercises/"})
    public String listAll(
        @RequestParam(defaultValue = "1") int pageNo,
        Model model
    ) {
        Page<LegExercise> page = legExerciseService.findPaginatedAndSorted(pageNo);
        List<LegExerciseDto> legExercises = page.getContent().stream().map(l -> legExerciseService.convertToDto(l)).toList();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("legExercises", legExercises);

        return "admin/legexercises/index";
    }

    @DeleteMapping({"leg-exercises/{id}", "leg-exercise/{id}/"})
    public String deleteLegExercise(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        legExerciseService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Leg exercise deleted successfully!");
        return "redirect:/admin/leg-exercises";
    }

}
