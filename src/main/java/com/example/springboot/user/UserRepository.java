package com.example.springboot.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);
    User getUserById(Long id);

    List<User> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"favoriteBooks"})
    User getWithFavoriteBooksByUsername(String username);
}
