package com.example.springboot.trainee;

import com.example.springboot.trainer.Trainer;
import com.example.springboot.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Setter
public class Trainee extends User{

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
/*

    @ManyToMany(mappedBy = "trainees")
    private Set<Trainer> trainers = new HashSet<>();
*/
    private Integer trainingActivity;

    public Trainee() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getTrainingActivity() {
        return trainingActivity;
    }

    public void setTrainingActivity(Integer trainingActivity) {
        this.trainingActivity = trainingActivity;
    }
}
