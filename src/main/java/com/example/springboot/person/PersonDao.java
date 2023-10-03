package com.example.springboot.person;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PersonDao {

    @PersistenceContext
    EntityManager em;

    public void save(Person entity) {
        em.persist(entity);
    }

    public void update(Person entity) {
        em.merge(entity);
    }

    public void delete(Person entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public Person findById(Long id) {
        return em.find(Person.class, id);
    }
}