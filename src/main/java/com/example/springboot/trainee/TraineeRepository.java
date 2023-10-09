package com.example.springboot.trainee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {
/*

    List<Trainee> findByUser_LastNameAndUser_ActiveTrue(String lastName);
    Trainee getTraineeByUser_LastName(String lastName);

    @Query("SELECT t FROM Trainer t INNER JOIN User u ON u.id = t.id WHERE u.lastName LIKE %?1%")

    List<Trainee> whosLastNameContainsText(String text);

    @Query("SELECT u FROM User u WHERE u.isTrainer = false")
    List<Trainee> getAllTrainees();
*/

    Trainee getTraineeById(Long id);

    List<Trainee> findAll();


}
