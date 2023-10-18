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
public class Trainee extends User{

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

    @OneToOne(fetch = FetchType.EAGER) // Eagerly fetch User data
    @JoinColumn(name = "id") // or specify the appropriate join column
    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", trainingActivity=" + trainingActivity +
                ", " + super.toString() +
                '}';
    }
}
