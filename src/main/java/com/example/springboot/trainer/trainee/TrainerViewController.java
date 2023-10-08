package com.example.springboot.trainer.trainer;

import com.example.springboot.trainer.trainee.TrainerRepository;
import com.example.springboot.user.User;
import com.example.springboot.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/view/trainer")
public class TrainerViewController {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    public TrainerViewController(TrainerRepository trainerRepository, UserRepository userRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/list")
    public String getListView(Model model) {
        model.addAttribute("users", userRepository.getUsersByIsTrainerTrue());
        model.addAttribute("trainers", trainerRepository.findAll());
        return "/trainers/list-view";
    }

    @GetMapping("/add")
    public String getAddView(Model model) {
       log.info("getAddView");
        model.addAttribute("user", new User());
        return "/trainers/add-view";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/trainers/add-view";
        }
        userRepository.save(user);
        return "redirect:/view/trainer/list";
    }
}
