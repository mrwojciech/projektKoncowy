package com.example.springboot.user;

import com.example.springboot.book.Book;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
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
    private Boolean active = false;

    @ToString.Exclude
    @OneToMany
    private Set<Book> favoriteBooks = new LinkedHashSet<>();
}
