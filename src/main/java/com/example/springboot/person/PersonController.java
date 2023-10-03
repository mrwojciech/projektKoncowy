package com.example.springboot.person;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @PostMapping
    public String create(Person person) {
        personDao.save(person);
        return person.toString();
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @PostMapping("/{id}")
    public String update( @PathVariable Long id, Person person) {
        Person existing = personDao.findById(id);
        existing.setEmail(person.getEmail());
        System.out.println(person);
        PersonDetails details = person.getDetails();
        existing.getDetails().setFirstName(details.getFirstName());
        existing.getDetails().setLastName(details.getLastName());
        personDao.update(existing);
        return existing.toString();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        return person.toString();
    }
}
