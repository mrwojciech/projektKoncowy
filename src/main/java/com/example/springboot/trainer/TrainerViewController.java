package com.example.springboot.trainer;

import com.example.springboot.schedule.AvailableSlot;
import com.example.springboot.schedule.SlotRepository;
import com.example.springboot.schedule.Schedule;
import com.example.springboot.schedule.ScheduleRepository;
import com.example.springboot.trainee.TraineeRepository;
import com.example.springboot.training.Training;
import com.example.springboot.training.TrainingRepository;
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
import java.time.LocalDateTime;

@Log4j2
@Controller
@RequestMapping("/view/trainer")
public class TrainerViewController {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final TraineeRepository traineeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ScheduleRepository scheduleRepository;
    private final TrainingRepository trainingRepository;
    private final SlotRepository slotRepository;

    public TrainerViewController(TrainerRepository trainerRepository, UserRepository userRepository, TraineeRepository traineeRepository, PasswordEncoder passwordEncoder, ScheduleRepository scheduleRepository, TrainingRepository trainingRepository, SlotRepository slotRepository) {
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
        this.traineeRepository = traineeRepository;
        this.passwordEncoder = passwordEncoder;
        this.scheduleRepository = scheduleRepository;
        this.trainingRepository = trainingRepository;
        this.slotRepository = slotRepository;
    }


    @GetMapping("/list")
    public String getListView(Model model) {
     /*   model.addAttribute("trainers", trainerRepository.findAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User byUsername = userRepository.getByUsername(userDetails.getUsername());
            Long id = byUsername.getId();
            model.addAttribute("userId", id);
        }*/
//        model.addAttribute("users", userRepository.getUsersByIsTrainerTrue());
        model.addAttribute("trainers", trainerRepository.findAll());
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
        trainer.setPassword(passwordEncoder.encode(trainer.getPassword()));
        trainer.setRole("USER");
        trainer.setActive(true);
        trainerRepository.save(trainer);
        return "redirect:/view/trainer/list";
    }

    @GetMapping("/{trainerId}/schedule")
    public String getSchedule(@PathVariable(name = "trainerId") Long trainerId,
                              Model model) {
        Schedule schedule = scheduleRepository.getScheduleByTrainerId(trainerId);
        model.addAttribute("schedule", schedule);
        model.addAttribute("trainerId", trainerId);
        return "/slots/list-view";
    }

    @RequestMapping("/bookSlot/{scheduleId}/{trainerId}/{slotId}")
    public String bookSlot(
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("trainerId") Long trainerId,
            @PathVariable("slotId") Integer slotId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = null;
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User byUsername = userRepository.getByUsername(userDetails.getUsername());
            userId = byUsername.getId();
        }

        Training training = new Training();
        training.setTrainer(trainerRepository.getTrainerById(trainerId));
        training.setUser(userRepository.getUserById(userId));
        Schedule scheduleById = scheduleRepository.getScheduleById(scheduleId);

        AvailableSlot availableSlot = scheduleById.getAvailableSlots().get(slotId - 1);
        training.setDateTime(availableSlot.getAvailableSlot());
        training.setDescription("pierwszy trening");
        trainingRepository.save(training);
//        scheduleRepository.removeSlotByScheduleId(scheduleId, slotId);
//        scheduleRepository.deleteScheduleByIdAndAvailableOneHourSlotsAndId(scheduleId, slotId);
        //scheduleRepository.getScheduleById(scheduleId).getAvailableOneHourSlots().remove(slotId-1);
        slotRepository.deleteById(Long.valueOf(slotId));
        return "redirect:/view/training/" + userId;
    }
}