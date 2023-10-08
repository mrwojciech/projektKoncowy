package com.example.springboot.trainer;

import com.example.springboot.trainee.TraineeRepository;
import com.example.springboot.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/view/trainer")
public class TrainerViewController {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final TraineeRepository traineeRepository;

    public TrainerViewController(TrainerRepository trainerRepository, UserRepository userRepository, TraineeRepository traineeRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
        this.traineeRepository = traineeRepository;
    }


    @GetMapping("/list")
    public String getListView(Model model) {
        model.addAttribute("users", userRepository.getUsersByIsTrainerTrue());
        model.addAttribute("trainers", trainerRepository.findAll());
        return "/trainers/list-view";
    }

    @GetMapping("/add")
    public String getAddView(Model model,
                             @RequestParam(name = "isTrainer", defaultValue = "false") Boolean isTrainer,
                             @RequestParam(name = "rating", defaultValue = "0") Integer rating) {
        log.info("getAddView");
        model.addAttribute("trainer", new Trainer());
        model.addAttribute("isTrainer", isTrainer);
        model.addAttribute("rating", rating);
        return "/trainers/add-view";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("trainer") @Valid Trainer trainer, BindingResult bindingResult,
                          @RequestParam(name = "trainer.rating", required = false) Double trainerrating) {
        if (bindingResult.hasErrors()) {
            return "/trainers/add-view";
        }
        trainer.setRating(trainer.getRating());
        trainerRepository.save(trainer);
        return "redirect:/view/trainer/list";
    }
}