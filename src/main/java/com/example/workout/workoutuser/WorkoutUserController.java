package com.example.workout.workoutuser;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private final WorkoutUserService service;

    @GetMapping({"", "/"})
    public String show(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Optional<WorkoutUserDto> workoutUserDto = service.findDtoByUsername(userDetails.getUsername());
        model.addAttribute("workoutUser", workoutUserDto.get());
        return "workoutusers/show";
    }

    @GetMapping({"/new", "/new/"})
    public String newForm(Model model) {
        model.addAttribute("workoutUser", new WorkoutUserPlain());
        return "workoutusers/new";
    }

    @PostMapping({"", "/"})
    public String create(@ModelAttribute WorkoutUserPlain workoutUserPlain, RedirectAttributes redirectAttributes) {
        try {
            service.create(workoutUserPlain);
        } catch (PasswordsNotTheSame e) {
            redirectAttributes.addFlashAttribute("error", "Passwords are not the same.");
            return "redirect:/users/new";
        }
        redirectAttributes.addFlashAttribute("success", "Account created successfully!");
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/edit", "/edit/"})
    public String editForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        WorkoutUser workoutUser = service.findByUsername(userDetails.getUsername()).get();
        WorkoutUserPlain workoutUserPlain = new WorkoutUserPlain(
            workoutUser.getEmail(),
            workoutUser.getUsername()
        );
        model.addAttribute("workoutUser", workoutUserPlain);
        return "workoutusers/edit";
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping({"", "/"})
    public String update(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute WorkoutUserPlain workoutUserPlain, RedirectAttributes redirectAttributes) {
        try {
            service.update(userDetails.getUsername(), workoutUserPlain);
        } catch (PasswordsNotTheSame e) {
            redirectAttributes.addFlashAttribute("error", "Passwords are not the same.");
            return "redirect:/users/edit";
        }
        redirectAttributes.addFlashAttribute("success", "Account updated successfully!");
        return "redirect:/users";
    }

    @DeleteMapping({"", "/"})
    public String destroy(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        service.delete(userDetails.getUsername());
        redirectAttributes.addFlashAttribute("success", "Account deleted successfully! Sorry to see you go.");
        return "redirect:/";
    }
}