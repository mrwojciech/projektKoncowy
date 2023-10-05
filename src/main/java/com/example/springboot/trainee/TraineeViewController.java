package com.example.springboot.trainee;

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
@RequestMapping("/view/trainee")
public class TraineeViewController {

    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;

    public TraineeViewController(TraineeRepository traineeRepository, UserRepository userRepository) {
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/list")
    public String getListView(Model model) {
        model.addAttribute("users", userRepository.getUsersByIsTrainerFalse());
        return "/trainees/list-view";
    }

    @GetMapping("/add")
    public String getAddView(Model model) {
       log.info("getAddView");
        model.addAttribute("user", new User());
        return "/trainees/add-view";
    }

    @PostMapping("/add")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/trainees/add-view";
        }
        userRepository.save(user);
        return "redirect:/view/trainee/list";
    }
}
