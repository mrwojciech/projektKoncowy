package com.example.springboot.training;

import com.example.springboot.trainee.TraineeRepository;
import com.example.springboot.trainer.TrainerRepository;
import com.example.springboot.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Log4j2
@Controller
@RequestMapping("/view/training")
public class TrainingViewController {

    private final TrainingRepository trainingRepository;
    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;

    public TrainingViewController(TrainingRepository trainingRepository, TrainerRepository trainerRepository, TraineeRepository traineeRepository, UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/trainee/{id}")
    @ResponseBody
    public String getAllTrainingsForTrainee(@RequestParam(name = "id") Long id) {
        return String.valueOf(trainingRepository.getAllByUser_Id(id));
    }

    @GetMapping("/trainer/{id}")
    @ResponseBody
    public String getAllTrainingsForTrainer(@RequestParam(name = "id") Long id) {
        return String.valueOf(trainingRepository.getAllByTrainerId(id));
    }

    @GetMapping("/add")
    @ResponseBody
    public String addTraining(@RequestParam(name = "trainerId") Long trainerId,
                              @RequestParam(name = "userId") Long userId) {
        Training training = new Training();
        training.setTrainer(trainerRepository.getTrainerById(trainerId));
        training.setUser(userRepository.getUserById(userId));
        trainingRepository.save(training);
        return "trainingAdded";
    }
}
