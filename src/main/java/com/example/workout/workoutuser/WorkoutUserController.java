package com.example.workout.workoutuser;

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
@RequestMapping("/users")
@RequiredArgsConstructor
public class WorkoutUserController {
    private final WorkoutUserRepository repository;
    private final WorkoutUserService service;

    @GetMapping({"", "/"})
    public String show(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        WorkoutUser workoutUser = repository.findByUsername(userDetails.getUsername()).get();
        WorkoutUserDto workoutUserDto = service.findById(workoutUser.getId()).get();
        model.addAttribute("workoutUser", workoutUserDto);
        return "workoutusers/show";
    }

    @GetMapping({"/new", "/new/"})
    public String newForm(Model model) {
        model.addAttribute("workoutUser", new WorkoutUserPlain());
        return "workoutusers/new";
    }

    @PostMapping({"", "/"})
    public String create(@ModelAttribute WorkoutUserPlain workoutUserPlain, RedirectAttributes redirectAttributes) {
        service.createWorkoutUser(workoutUserPlain);
        redirectAttributes.addFlashAttribute("success", "User created successfully!");
        return "redirect:/login";
    }

    @GetMapping({"/{id}/edit", "/{id}/edit/"})
    public String editForm(@PathVariable Long id, Model model) {
        Optional<WorkoutUser> workoutUser = repository.findById(id);
        if (workoutUser.isEmpty()) {
            return "redirect:/leg-exercises";
        }
        model.addAttribute("workoutUser", workoutUser.get());
        return "workoutusers/edit";
    }

    @PutMapping({"/{id}", "/{id}/"})
    public String update(@PathVariable Long id, @ModelAttribute WorkoutUser workoutUser, RedirectAttributes redirectAttributes) {
        workoutUser.setId(id);
        repository.save(workoutUser);
        redirectAttributes.addFlashAttribute("success", "User updated successfully!");
        return "redirect:/users/{id}";
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public String destroy(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "User deleted successfully!");
        return "redirect:/leg-exercises";
    }
}