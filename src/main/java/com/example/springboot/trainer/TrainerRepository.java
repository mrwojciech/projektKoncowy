package com.example.springboot.trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Trainer getTrainerById(Long id);
/*

    Trainer getTraineeByUser_LastName(String lastName);

    @Query("SELECT t FROM Trainer t INNER JOIN User u ON u.id = t.id WHERE u.lastName LIKE %?1%")

    List<Trainer> whosLastNameContainsText(String text);

    List<Trainer> findAllByUser_IsTrainer(boolean b);
*/

    List<Trainer> findAll();
    /*
    @Query("SELECT u FROM User u WHERE u.isTrainer = false")
    List<Trainer> getAllTr();
*/
}
