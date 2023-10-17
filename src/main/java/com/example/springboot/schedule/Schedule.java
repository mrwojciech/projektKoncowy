package com.example.springboot.schedule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(name = "schedule")
public class Schedule {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trainer_id")
    private Long trainerId;


    @CollectionTable(name = "schedule_available_slots", joinColumns = @JoinColumn(name = "schedule_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "available_slots")
    private List<LocalDateTime> availableOneHourSlots;

}
