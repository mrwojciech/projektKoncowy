package com.example.springboot.trainer;

import com.example.springboot.schedule.AvailableSlot;
import com.example.springboot.schedule.Schedule;
import com.example.springboot.schedule.ScheduleRepository;
import com.example.springboot.schedule.SlotRepository;
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
import java.util.List;

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
        model.addAttribute("trainers", trainerRepository.findAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User byUsername = userRepository.getByUsername(userDetails.getUsername());
            Long id = byUsername.getId();
            model.addAttribute("user", userRepository.getUserById(id));
        }
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
        trainer.setTrainer(true);
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


    @GetMapping("/releaseSlot")
    public String releaseSlot(@RequestParam(name = "trainingId") Long trainingId) {

        Long userId = getUserId();

        Training training = trainingRepository.getTrainingById(trainingId);
        Long trainerId = training.getTrainer().getId();
        Long scheduleId = scheduleRepository.findByTrainerId(trainerId).getId();


        AvailableSlot releaseSlot = new AvailableSlot();
        releaseSlot.setAvailableSlot(training.getDateTime());
        releaseSlot.setId(scheduleId);
        List<AvailableSlot> availableSlots = scheduleRepository.getScheduleById(scheduleId).getAvailableSlots();
        availableSlots.add(releaseSlot);
        Schedule schedule = new Schedule();
        schedule.setAvailableSlots(availableSlots);
        schedule.setId(scheduleId);
        schedule.setTrainerId(trainerId);
        scheduleRepository.save(schedule);

        trainingRepository.delete(training);
        return "redirect:/view/training/" + userId;
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = null;
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User byUsername = userRepository.getByUsername(userDetails.getUsername());
            userId = byUsername.getId();
        }
        return userId;
    }

    @GetMapping("/bookSlot/{scheduleId}/{trainerId}/{slotId}")
    public String bookSlot(
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("trainerId") Long trainerId,
            @PathVariable("slotId") Integer slotId) {

        Long userId = getUserId();

        Training training = new Training();
        training.setTrainer(trainerRepository.getTrainerById(trainerId));
        training.setUser(userRepository.getUserById(userId));
        Schedule scheduleById = scheduleRepository.getScheduleById(scheduleId);

        AvailableSlot availableSlot = slotRepository.getAvailableSlotById(Long.valueOf(slotId));
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