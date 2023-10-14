package com.example.springboot.trainee;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    private final TraineeRepository traineeRepository;

    public TraineeController(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    /*
        @GetMapping("/{id}")
        @ResponseBody
        public String getUserById(@PathVariable(name = "id") Long id) {
            return String.valueOf(traineeRepository.findByU(id));
        }*/
    @GetMapping("/traineeLandingPage")
    @ResponseBody
    public String traineeLandingPage(Model model) {
        return "/trainee/my-trainings";
    }

}
