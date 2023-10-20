package com.example.springboot.trainer;

import com.example.springboot.user.User;
import com.example.springboot.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    public TrainerController(TrainerRepository trainerRepository, UserRepository userRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getUserById(@PathVariable(name = "id") Long id) {
        return String.valueOf(trainerRepository.getTrainerById(id));
    }

    @GetMapping("/list")
    @ResponseBody
    public String findAll() {
        return String.valueOf(trainerRepository.findAll());
    }



}
