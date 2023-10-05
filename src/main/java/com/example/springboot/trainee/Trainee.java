package com.example.springboot.trainee;

import com.example.springboot.user.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trainee")
@ToString
@Getter
@Setter
public class Trainee {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private User user;

    public Trainee() {
    }


    public void setId(Long id) {
        this.id = id;
    }

}
