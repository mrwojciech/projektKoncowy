package com.example.springboot.trainer;

import com.example.springboot.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "trainer")
@ToString
@Getter
@Setter
public class Trainer {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "rating")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "10.0", inclusive = true)
    @Digits(integer = 2, fraction = 1)
    private Double rating;

    @OneToOne(cascade = CascadeType.ALL)
//    @NotNull
    private User user;



    public void setId(Long id) {
        this.id = id;
    }

}
