package com.example.springboot.trainee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    @Query("SELECT u.id FROM User u INNER JOIN Trainee t ON u.id = t.id WHERE t.id =:traineeId")
    Long getUserIdByTraineeId(Long traineeId);
/*

    List<Trainee> findByUser_LastNameAndUser_ActiveTrue(String lastName);
    Trainee getTraineeByUser_LastName(String lastName);


    List<Trainee> whosLastNameContainsText(String text);

    @Query("SELECT u FROM User u WHERE u.isTrainer = false")
    List<Trainee> getAllTrainees();
*/

    Trainee getTraineeById(Long id);

    List<Trainee> findAll();

    /*

        @Query("SELECT t FROM Trainee t INNER JOIN User u ON u.id = t.id WHERE u.id = :userId")
        Trainee getTraineeByUserId(@Param("userId") Long userId);
    */
    @Query("SELECT t FROM Trainee t  INNER JOIN User u ON t.id = u.id WHERE t.id = :id")
    Trainee getTraineeByUserId(Long id);
}
