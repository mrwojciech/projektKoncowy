package com.example.springboot.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findByTrainerId(Long id);

    Schedule getScheduleById(Long id);


    Schedule getScheduleByTrainerId(Long trainerId);
}
