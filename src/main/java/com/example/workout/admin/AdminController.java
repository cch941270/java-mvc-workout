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
import com.example.workout.legexercise.LegExerciseType;
import com.example.workout.workoutuser.WorkoutUser;
import com.example.workout.workoutuser.WorkoutUserDto;
import com.example.workout.workoutuser.WorkoutUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LegExerciseService legExerciseService;
    private final WorkoutUserService workoutUserService;
    private final AdminService service;

    @GetMapping({"statistics", "statistics/"})
    public String statistics(Model model) {
        model.addAttribute("numberOfUsers", service.numberOfUsers());
        model.addAttribute("numberOfLegExercises", service.numberOfLegExercises());
        model.addAttribute("numberOfLunge", service.numberOfLegExercisesByType(LegExerciseType.LUNGE));
        model.addAttribute("numberOfSquat", service.numberOfLegExercisesByType(LegExerciseType.SQUAT));
        model.addAttribute("numberOfStepUp", service.numberOfLegExercisesByType(LegExerciseType.STEP_UP));
        model.addAttribute("numberOfLungeCount", service.numberOfLegExerciseCountsByType(LegExerciseType.LUNGE));
        model.addAttribute("numberOfSquatCount", service.numberOfLegExerciseCountsByType(LegExerciseType.SQUAT));
        model.addAttribute("numberOfStepUpCount", service.numberOfLegExerciseCountsByType(LegExerciseType.STEP_UP));
        return "admin/statistics/index";
    }

    @GetMapping({"leg-exercises", "leg-exercises/"})
    public String listAll(
        @RequestParam(defaultValue = "1") int pageNo,
        Model model
    ) {
        Page<LegExercise> page = service.findPaginatedAndSorted(pageNo);
        List<LegExerciseDto> legExercises = page.getContent().stream().map(l -> legExerciseService.convertToDto(l)).toList();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("legExercises", legExercises);

        return "admin/legexercises/index";
    }

    @DeleteMapping({"leg-exercises/{id}", "leg-exercise/{id}/"})
    public String deleteLegExercise(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("success", "Leg exercise deleted successfully!");
        return "redirect:/admin/leg-exercises";
    }

    @GetMapping({"users", "users/"})
    public String listAllUsers(
        @RequestParam(defaultValue = "1") int pageNo,
        @RequestParam Optional<String> email,
        @RequestParam Optional<String> sortBy,
        Model model
    ) {
        Page<WorkoutUser> page = service.findPaginatedAndSorted(pageNo, email, sortBy);
        List<WorkoutUserDto> workoutUsers = page.getContent().stream().map(w -> workoutUserService.convertToDto(w)).toList();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("workoutUsers", workoutUsers);

        return "admin/workoutusers/index";
    }

    @DeleteMapping({"users/{id}", "users/{id}/"})
    public String deleteWorkoutUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("success", "Workout user deleted successfully!");
        return "redirect:/admin/users";
    }

    @GetMapping({"online-users", "online-users/"})
    public String listAllOnlineUsers(Model model) {
        List<WorkoutUser> allOnlineUsers = service.findAllOnline();
        model.addAttribute("allOnlineUsers", allOnlineUsers);
        return "admin/workoutusers/online-users";
    }

    @PostMapping({"users/{id}", "users/{id}/"})
    public String logoutUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.logout(id);
        redirectAttributes.addFlashAttribute("success", "Logout user successfully!");
        return "redirect:/admin/online-users";
    }

}
