package com.example.workout.legexercise;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/leg-exercises")
public class LegExerciseController {
    private final LegExerciseRepository legExerciseRepository;
    private final LegExerciseService legExerciseService;

    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<LegExercise> legExercises = legExerciseService.findAllByUsernameSorted(userDetails.getUsername());
        model.addAttribute("legExercises", legExercises);
        return "legexercises/index";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String show(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<LegExercise> legExercise = legExerciseService.findByUsernameAndId(userDetails.getUsername(), id);
        if (legExercise.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Leg exercise not found.");
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
    public String create(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute LegExercise legExercise, RedirectAttributes redirectAttributes) {
        legExerciseService.create(userDetails.getUsername(), legExercise);
        redirectAttributes.addFlashAttribute("success", "Leg exercise created successfully!");
        return "redirect:/leg-exercises";
    }

    @GetMapping({"/{id}/edit", "/{id}/edit/"})
    public String editForm(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<LegExercise> legExercise = legExerciseService.findByUsernameAndId(userDetails.getUsername(), id);
        if (legExercise.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Leg exercise not found.");
            return "redirect:/leg-exercises";
        }
        model.addAttribute("legExercise", legExercise.get());
        model.addAttribute("allLegExerciseTypes", LegExerciseType.values());
        return "legexercises/edit";
    }

    @PutMapping({"/{id}", "/{id}/"})
    public String update(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, @ModelAttribute LegExercise updatedLegExercise, RedirectAttributes redirectAttributes) {
        Optional<LegExercise> legExercise = legExerciseService.findByUsernameAndId(userDetails.getUsername(), id);
        if (legExercise.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Leg exercise not found.");
            return "redirect:/leg-exercises";
        }
        legExerciseService.update(legExercise.get(), updatedLegExercise);
        redirectAttributes.addFlashAttribute("success", "Leg exercise updated successfully!");
        return "redirect:/leg-exercises/{id}";
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public String destroy(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<LegExercise> legExercise = legExerciseService.findByUsernameAndId(userDetails.getUsername(), id);
        if (legExercise.isPresent()) {
            legExerciseRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Leg exercise deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Leg exercise not found.");
        }
        return "redirect:/leg-exercises";
    }
}
