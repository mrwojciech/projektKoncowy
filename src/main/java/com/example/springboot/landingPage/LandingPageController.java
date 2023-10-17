package com.example.springboot.landingPage;

import com.example.springboot.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {

    private final UserRepository userRepository;

    public LandingPageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/userLanding")
    public String userLandingPage() {
        return "userLanding"; // Return the view name for the user landing page
    }

    @GetMapping("/adminLanding")
    public String adminLandingPage() {
        return "adminLanding"; // Return the view name for the admin landing page
    }

    @GetMapping("/defaultLanding")
    public String defaultLandingPage() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (GrantedAuthority auth : userDetails.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                return "redirect:/admin/adminLandingPage";
            }
            if (userRepository.getByUsername(userDetails.getUsername()).getTrainer()) {
                return "redirect:/trainer/trainerLandingPage";
            }
        }
        return "redirect:/view/trainee/traineeLandingPage";
    }
}
