package com.example.springboot.book;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import  com.example.springboot.author.Author;
import  com.example.springboot.publisher.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager em;

    public void save(Book entity) {
        em.persist(entity);
    }

    public void update(Book entity) {
        em.merge(entity);
    }

    public void delete(Book entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public Book findById(Long id) {
        Book book = em.find(Book.class, id);
//        Hibernate.initialize(book.getAuthors());
        return book;
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b FETCH ALL PROPERTIES", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllByRating(Integer rating) {
        return em.createQuery("SELECT b FROM Book b WHERE b.rating = :rating", Book.class)
                .setParameter("rating", rating)
                .getResultList();
//
//        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.rating = :rating", Book.class);
//        query.setParameter("rating", rating);
//        List<Book> books = query.getResultList();
//        return books;
    }

    public List<Book> findAllWithPublisher() {
        return em.createQuery("SELECT b FROM Book b JOIN b.publisher", Book.class).getResultList();
    }

    public List<Book> findAllByPublisher(Publisher publisher) {
        return em.createQuery("SELECT b FROM Book b WHERE b.publisher = :publisher", Book.class)
                .setParameter("publisher", publisher)
                .getResultList();
    }

    public List<Book> findAllByAuthor(Author author) {
        return em.createQuery("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors WHERE :author MEMBER OF b.authors", Book.class)
                .setParameter("author", author)
                .getResultList();
    }
}
