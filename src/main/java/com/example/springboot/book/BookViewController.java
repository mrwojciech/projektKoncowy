package com.example.springboot.book;

import com.example.springboot.author.AuthorDao;
import com.example.springboot.publisher.PublisherDao;
import com.example.springboot.user.User;
import com.example.springboot.user.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/view/book")
public class BookViewController {

    private final BookDao bookDao;
    private final BookRepository bookRepository;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;
    private final UserRepository userRepository;

    public BookViewController(BookDao bookDao, BookRepository bookRepository, AuthorDao authorDao, PublisherDao publisherDao,
                              UserRepository userRepository) {
        this.bookDao = bookDao;
        this.bookRepository = bookRepository;
        this.authorDao = authorDao;
        this.publisherDao = publisherDao;
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String getListView(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "/books/list-view";
    }

    @GetMapping("/add")
    public String getAddView(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("publisherList", publisherDao.findAll());
        model.addAttribute("authorList", authorDao.findAll());
        return "/books/add-view";
    }

    @PostMapping("/add")
    public String addBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Parametr Book book jest automatycznie umieszczany w modelu pod nazwą
            // "book", która wynika z nazwy klasy, a nie nazwy parametru.
            // Nie trzeba używać: model.addAttribute("book", book);
            //
            // Umieszczenie pod inną nazwą wymaga użycia @ModelAttribute, np.
            // @ModelAttribute("addedBook") Book book.
            model.addAttribute("publisherList", publisherDao.findAll());
            model.addAttribute("authorList", authorDao.findAll());
            return "/books/add-view";
        }
        bookRepository.save(book);
        return "redirect:/view/book/list";
    }

    @GetMapping("/update")
    public String getUpdateView(Model model, @RequestParam Long id) {
        model.addAttribute("book", bookDao.findById(id));
        model.addAttribute("publisherList", publisherDao.findAll());
        model.addAttribute("authorList", authorDao.findAll());
        return "/books/update-view";
    }

    @PostMapping("/update")
    public String updateBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("publisherList", publisherDao.findAll());
            model.addAttribute("authorList", authorDao.findAll());
            return "/books/update-view";
        }
        bookDao.update(book);
        return "redirect:/view/book/list";
    }

    @GetMapping("/delete")
    public String getDeleteView(Model model, @RequestParam Long id) {
        model.addAttribute("book", bookDao.findById(id));
        return "/books/delete-view";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "redirect:/view/book/list";
    }

    @GetMapping("/my-list")
    public String getMyListView(@AuthenticationPrincipal UserDetails authenticatedUser, Model model) {
        User user = userRepository.getWithFavoriteBooksByUsername(authenticatedUser.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("myBooks", user.getFavoriteBooks());
        return "/books/my-list-view";
    }

    @GetMapping("/addToFavorite")
    public String addToFavorite(@RequestParam Long id, @AuthenticationPrincipal UserDetails authenticatedUser) {
        Book book = bookRepository.findById(id).get();
        User user = userRepository.getWithFavoriteBooksByUsername(authenticatedUser.getUsername());
        user.getFavoriteBooks().add(book);
        userRepository.save(user);
        return "redirect:/view/book/list";
    }

    @GetMapping("/removeFromFavorite")
    public String removeFromFavorite(@RequestParam Long id, @AuthenticationPrincipal UserDetails authenticatedUser) {
        User user = userRepository.getWithFavoriteBooksByUsername(authenticatedUser.getUsername());
        user.getFavoriteBooks().removeIf(book -> book.getId().equals(id));
        userRepository.save(user);
        return "redirect:/view/book/my-list";
    }

}
