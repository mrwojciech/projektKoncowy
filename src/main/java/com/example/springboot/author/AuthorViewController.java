package com.example.springboot.author;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/view/author")
public class AuthorViewController {

    private final AuthorDao authorDao;

    public AuthorViewController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping("/list")
    public String getListView(Model model) {
        model.addAttribute("authors", authorDao.findAll());
        return "/authors/list-view";
    }

    @GetMapping("/add")
    public String getAddView(Model model) {
        model.addAttribute("author", new Author());
        return "/authors/add-view";
    }

    @PostMapping("/add")
    public String addAuthor(@Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/authors/add-view";
        }
        authorDao.save(author);
        return "redirect:/view/author/list";
    }

    @GetMapping("/update")
    public String getUpdateView(Model model, @RequestParam Long id) {
        model.addAttribute("author", authorDao.findById(id));
        return "/authors/update-view";
    }

    @PostMapping("/update")
    public String updateAuthor(@Valid Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/authors/update-view";
        }
        authorDao.update(author);
        return "redirect:/view/author/list";
    }

    @GetMapping("/delete")
    public String getDeleteView(Model model, @RequestParam Long id) {
        model.addAttribute("author", authorDao.findById(id));
        return "/authors/delete-view";
    }

    @PostMapping("/delete")
    public String deleteAuthor(@RequestParam Long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "redirect:/view/author/list";
    }
}
