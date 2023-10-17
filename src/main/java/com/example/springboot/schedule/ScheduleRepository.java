package com.example.springboot.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    Schedule getAllById(Long id);
    Schedule getScheduleByTrainerId(Long id);

    Schedule getScheduleById(Long id);

}
