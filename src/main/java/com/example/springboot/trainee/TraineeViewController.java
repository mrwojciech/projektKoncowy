package com.example.springboot.trainee;

import com.example.springboot.trainer.Trainer;
import com.example.springboot.trainer.TrainerRepository;
import com.example.springboot.user.User;
import com.example.springboot.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "/trainees/list-view";
    }

    @GetMapping("/add")
    public String getAddView(Model model,
                             @RequestParam(name = "isTrainer", defaultValue = "false") Boolean isTrainer) {
        log.info("getAddView");
        model.addAttribute("user", new User());
        model.addAttribute("isTrainer", isTrainer);
        return "/trainees/add-view";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult bindingResult,
                          @RequestParam(name = "rating", required = false) Double rating, Model model) {
        if (bindingResult.hasErrors()) {
            return "/trainees/add-view";
        }



        if (user.getIsTrainer()) {
            userRepository.save(user);
            Trainer trainer = new Trainer();
            trainer.setRating(rating);
            trainer.setUser(user);
            trainerRepository.save(trainer);
        }

        if (!user.getIsTrainer()) {
            userRepository.save(user);
            Trainee trainee = new Trainee();
            trainee.setUser(user);
            traineeRepository.save(trainee);
        }

//        userRepository.save(user);

        return "redirect:/view/trainee/list";
    }
}
