package com.example.springboot.trainee;

import com.example.springboot.trainer.TrainerRepository;
import com.example.springboot.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/view/trainee")
public class TraineeViewController {

    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    public TraineeViewController(TraineeRepository traineeRepository, TrainerRepository trainerRepository, UserRepository userRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/list")
    public String getListView(Model model) {
        model.addAttribute("users", userRepository.getUsersByIsTrainerFalse());
        model.addAttribute("trainees", traineeRepository.findAll());
        return "/trainees/list-view";
    }

    @GetMapping("/add")
    public String getAddView(Model model,
                             @RequestParam(name = "isTrainer", defaultValue = "false") Boolean isTrainer,
                             @RequestParam(name = "rating", defaultValue = "0") Integer rating

    ) {
        log.info("getAddView");
        model.addAttribute("trainee", new Trainee());
        model.addAttribute("isTrainer", isTrainer);
        model.addAttribute("rating", rating);
        return "/trainees/add-view";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("trainee") @Valid Trainee trainee, BindingResult bindingResult,
                          @RequestParam(name = "trainer.rating", required = false) Double trainerrating, Model model) {
        if (bindingResult.hasErrors()) {
            return "/trainees/add-view";
        }
        traineeRepository.save(trainee);
        return "redirect:/view/trainee/list";
    }


}
