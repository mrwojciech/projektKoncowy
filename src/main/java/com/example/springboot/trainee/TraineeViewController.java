package com.example.springboot.trainee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view/trainee")
public class TraineeViewController {

    private final TraineeRepository traineeRepository;

    public TraineeViewController(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }
}
