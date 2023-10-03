package com.example.springboot.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author getByEmail(String email);

    Optional<Author> findByEmail(String email);

    Author getByPesel(String pesel);

    Optional<Author> findByPesel(String pesel);

    List<Author> findAllByLastName(String lastName);

    @Query("SELECT a FROM Author a WHERE a.email LIKE ?1%")
    List<Author> whoseEmailStartsWithMyText(String text);

    @Query("SELECT a FROM Author a WHERE a.pesel LIKE ?1%")
    List<Author> whosePeselStartsWith(String prefix);
}
