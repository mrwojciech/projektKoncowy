package com.example.springboot.trainee;

import org.springframework.web.bind.annotation.*;

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

}
