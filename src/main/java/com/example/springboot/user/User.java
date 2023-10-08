package com.example.springboot.user;

import com.example.springboot.book.Book;
import com.example.springboot.trainee.Trainee;
import com.example.springboot.trainer.Trainer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String username;
    @Column(nullable = false)
    @NotBlank
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private Boolean isTrainer = false;


    @ToString.Exclude
    @OneToMany
    private Set<Book> favoriteBooks = new LinkedHashSet<>();


}
