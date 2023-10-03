package com.example.springboot.author;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager em;

    public void save(Author entity) {
        em.persist(entity);
    }

    public void update(Author entity) {
        em.merge(entity);
    }

    public void delete(Author entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public Author findById(Long id) {
        return em.find(Author.class, id);
    }

    public List<Author> findAll() {
        return em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }
}