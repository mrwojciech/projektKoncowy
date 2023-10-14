package com.example.springboot.trainer;

import com.example.springboot.user.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerRepository trainerRepository;

    public TrainerController(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
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

    @GetMapping("/trainerLandingPage")
    @ResponseBody
    public String trainerLandingPage(Model model) {
        return "/trainer/my-trainings";
    }


}
