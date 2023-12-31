package com.example.springboot.trainer;

import com.example.springboot.user.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@Entity
public class Trainer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "rating")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "10.0", inclusive = true)
    @Digits(integer = 2, fraction = 1)
    private Double rating;


    @OneToOne(fetch = FetchType.EAGER) // Eagerly fetch User data
    @JoinColumn(name = "id") // or specify the appropriate join column
    private User user;
/*

    @ManyToMany()
    private Set<Trainee> trainees = new HashSet<>();
*/

    @Override
    public Long getId() {
        return id;
    }

    public Double getRating() {
        return rating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", rating=" + rating +
                ", " + super.toString() +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
