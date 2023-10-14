package com.example.springboot.trainer;

import com.example.springboot.trainee.TraineeRepository;
import com.example.springboot.user.User;
import com.example.springboot.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        model.addAttribute("trainers", trainerRepository.findAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User byUsername = userRepository.getByUsername(userDetails.getUsername());
            Long id = byUsername.getId();
            model.addAttribute("userId", id);
        }
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
        //    model.addAttribute("trainee.id", id);
        return "/trainers/add-view";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("trainer") @Valid Trainer trainer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/trainers/add-view";
        }
        //      trainer.setRating(trainer.getRating());
        trainer.setRole("USER");
        trainerRepository.save(trainer);
        return "redirect:/view/trainer/list";
    }
}