package com.example.springboot.publisher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/view/publisher")
public class PublishViewController {

    private final PublisherDao publisherDao;

    public PublishViewController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @GetMapping("/list")
    public String getListView(Model model) {
        model.addAttribute("publishers", publisherDao.findAll());
        return "/publishers/list-view";
    }

    @GetMapping("/add")
    public String getAddView(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "/publishers/add-view";
    }

    @PostMapping("/add")
    public String addPublisher(@Valid Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/publishers/add-view";
        }
        publisherDao.save(publisher);
        return "redirect:/view/publisher/list";
    }

    @GetMapping("/update")
    public String getUpdateView(Model model, @RequestParam Long id) {
        model.addAttribute("publisher", publisherDao.findById(id));
        return "/publishers/update-view";
    }

    @PostMapping("/update")
    public String updatePublisher(@Valid Publisher publisher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/publishers/update-view";
        }
        publisherDao.update(publisher);
        return "redirect:/view/publisher/list";
    }

    @GetMapping("/delete")
    public String getDeleteView(Model model, @RequestParam Long id) {
        model.addAttribute("publisher", publisherDao.findById(id));
        return "/publishers/delete-view";
    }

    @PostMapping("/delete")
    public String deletePublisher(@RequestParam Long id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "redirect:/view/publisher/list";
    }
}
