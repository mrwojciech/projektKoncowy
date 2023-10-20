package com.example.springboot.trainee;

import com.example.springboot.trainer.TrainerRepository;
import com.example.springboot.user.User;
import com.example.springboot.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/view/trainee")
public class TraineeViewController {

    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TraineeViewController(TraineeRepository traineeRepository, TrainerRepository trainerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/list")
    public String getListView(Model model) {
//        model.addAttribute("users", userRepository.getUsersByIsTrainerFalse());
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
//        model.addAttribute("user",traineeRepository.getUserIdByTraineeId(tr));
        return "/trainees/add-view";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("trainee") @Valid Trainee trainee, BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "/trainees/add-view";
        }
        trainee.setPassword(passwordEncoder.encode(trainee.getPassword()));
        trainee.setRole("USER");
        trainee.setActive(true);
        traineeRepository.save(trainee);
/*
        Long userIdByTraineeId = traineeRepository.getUserIdByTraineeId(trainee.getId());
        List<User> users = userRepository.findAll();
*/
   //     model.addAttribute("trainees",trainee);
        return "redirect:/view/trainee/list";
    }

    @GetMapping("/traineeLandingPage")
    public String traineeLandingPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User byUsername = userRepository.getByUsername(userDetails.getUsername());
            Long id = byUsername.getId();
//            traineeRepository.findTraineeById(id);
            model.addAttribute("trainees", traineeRepository.getTraineeById(id));
        }
        return "/trainees/trainee-landingPage";
    }


}
