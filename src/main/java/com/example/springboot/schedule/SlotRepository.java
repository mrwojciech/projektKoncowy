package com.example.springboot.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<AvailableSlot, Long> {



}
