package com.example.springboot.trainee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    Trainee getTraineeById(Long id);

    Trainee getTraineeByUser_LastName(String lastName);

    @Query("SELECT t FROM Trainee t INNER JOIN User u ON u.id = t.id WHERE u.lastName LIKE %?1%")

    List<Trainee> whosLastNameContainsText(String text);

    @Query("SELECT u FROM User u WHERE u.isTrainer = false")
    List<Trainee> getAllTrainees();

}
