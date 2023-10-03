package com.example.springboot.person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view/person")
public class PersonViewController {

    private final PersonDao personDao;

    public PersonViewController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping("/old-way")
    public String getViewOldWay() {
        // request.getRequestDispatcher("person-old.jsp").forward(request, response);
        return "person-old";
    }

    @GetMapping("/new-way")
    public String getViewNewWay(Model model) {
        model.addAttribute("person", new Person());
        return "person-new";
    }

    @PostMapping("/old-way")
    public String createPersonOldWay(@RequestParam String login,
                                     @RequestParam String email,
                                     @RequestParam String password) {
        Person person = new Person();
        person.setLogin(login);
        person.setEmail(email);
        person.setPassword(password);
        personDao.save(person);
        // response.sendRedirect("/view/person/old-way");
        return "redirect:/view/person/old-way";
    }

    @PostMapping("/new-way")
    public String createPersonNewWay(Person person) {
        personDao.save(person);
        return "redirect:/view/person/new-way";
    }
}
