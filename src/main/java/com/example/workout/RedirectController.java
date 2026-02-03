package com.example.workout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToLocation() {
        return "redirect:/leg-exercises";
    }
}
