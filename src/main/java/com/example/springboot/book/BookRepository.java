package com.example.springboot.book;

import com.example.springboot.author.Author;
import com.example.springboot.category.Category;
import com.example.springboot.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    List<Book> gimmeBookWithMaTitle(String title);

    List<Book> findAllByCategory(Category category);

    @Query("SELECT b FROM Book b WHERE b.category = ?1")
    List<Book> jaMamKategorięTyMiDajKsiążki(Category category);

    List<Book> findAllByCategory_Id(Long categoryId);

    List<Book> findAllByCategory_Name(String categoryName);

    List<Book> findAllByAuthorsContains(Author author);

    List<Book> findAllByPublisherEquals(Publisher publisher);

    List<Book> findAllByPublisherIn(List<Publisher> publisherList);

    List<Book> findAllByRating(Integer rating);

    Book findFirstBookByCategoryOrderByTitle(Category category);

    // SELECT * FROM books
    // SELECT * FROM books WHERE category_id = :category
    // SELECT * FROM books WHERE category_id = :category ORDER BY title
    // SELECT * FROM books WHERE category_id = :category ORDER BY title LIMIT 1
    // ------------------

    @Query("SELECT b FROM Book b WHERE b.rating BETWEEN ?1 AND ?2")
    List<Book> gimmeBooksForRating(Integer from, Integer to);

    @Query("SELECT b FROM Book b WHERE b.publisher = ?1")
    List<Book> gimmeBooksForMyLovelyPublisher(Publisher publisher);

    @Query(value = "SELECT b.* FROM books b WHERE b.category_id = ?1 ORDER BY b.title LIMIT 1", nativeQuery = true)
    List<Book> gimmeBlahBlahBlah(Category category);

}
