package com.example.springboot.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> getAllByUser_Id(Long id);

    List<Training> getAllByTrainerId(Long id);

    Training getTrainingById(Long id);
}
