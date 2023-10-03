package com.example.springboot.book;

import org.springframework.web.bind.annotation.*;
import  com.example.springboot.author.Author;
import  com.example.springboot.author.AuthorDao;
import  com.example.springboot.publisher.Publisher;
import  com.example.springboot.publisher.PublisherDao;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping
    public String findAll() {
        return bookDao.findAll().stream()
                .map(b -> String.format("%d. '%s', rating: %d", b.getId(), b.getTitle(), b.getRating()))
                .collect(Collectors.joining("\n"));
    }

    @GetMapping("/byRating")
    public String findAllByRating(Integer rating) {
        return bookDao.findAllByRating(rating).stream()
                .map(b -> String.format("%d. '%s', rating: %d", b.getId(), b.getTitle(), b.getRating()))
                .collect(Collectors.joining("\n"));
    }

    @GetMapping("/withPublisher")
    public String findAllWithPublisher() {
        return bookDao.findAllWithPublisher().stream()
                .map(b -> String.format("%d. '%s', rating: %d, publisher: %s", b.getId(), b.getTitle(), b.getRating(), b.getPublisher()))
                .collect(Collectors.joining("\n"));
    }

    @GetMapping("/byPublisher")
    public String findAllByPublisher(Long publisherId) {
        Publisher publisher = publisherDao.findById(publisherId);
        return bookDao.findAllByPublisher(publisher).stream()
                .map(b -> String.format("%d. '%s', rating: %d, publisher: %s", b.getId(), b.getTitle(), b.getRating(), b.getPublisher()))
                .collect(Collectors.joining("\n"));

    }

    @GetMapping("/byAuthor")
    public String findAllByAuthor(Long authorId) {
        Author author = authorDao.findById(authorId);
        return bookDao.findAllByAuthor(author).stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n"));
    }

    @GetMapping("/get")
    public String get(@RequestParam Long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @PostMapping("/create")
    public String create(@RequestParam String title, @RequestParam Integer rating, @RequestParam Long publisherId, @RequestParam List<Long> authorIds) {
        Publisher publisher = publisherDao.findById(publisherId);
        Book book = new Book();
        book.setTitle(title);
        book.setRating(rating);
        book.setPublisher(publisher);
        for (Long authorId : authorIds) {
            Author author = authorDao.findById(authorId);
            book.getAuthors().add(author);
        }
        bookDao.save(book);
        return book.toString();
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String title, @RequestParam Integer rating) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        book.setRating(rating);
        bookDao.update(book);
        return book.toString();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return book.toString();
    }
}