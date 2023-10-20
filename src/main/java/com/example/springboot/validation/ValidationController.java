package com.example.springboot.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.awt.print.Book;
import java.util.Set;

@Controller
public class ValidationController {

    private final Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping("/validate")
    @ResponseBody
    public String validate() {
/*
        Book invalidBook = new Book();
        invalidBook.setTitle("Abc");
        invalidBook.setRating(-5);
        invalidBook.setPages(-10);
        invalidBook.setPublisher(null);
        invalidBook.setDescription("*".repeat(1000));

        Set<ConstraintViolation<Book>> violations = validator.validate(invalidBook);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Book> violation : violations) {
                String invalidField = violation.getPropertyPath().toString();
                Object invalidValue = violation.getInvalidValue();
                String validationMessage = violation.getMessage();
                System.out.println("Dla pola " + invalidField + " niepoprawna wartość " + invalidValue + " : " + validationMessage);
            }
        }
*/
        return "ok";
    }

    @GetMapping("/view-validate")
    public String validateWithView(Model model) {
/*
        Book invalidBook = new Book();
        invalidBook.setTitle("Abc");
        invalidBook.setRating(-5);
        invalidBook.setPages(-10);
        invalidBook.setPublisher(null);
        invalidBook.setDescription("*".repeat(1000));

        Set<ConstraintViolation<Book>> violations = validator.validate(invalidBook);
        model.addAttribute("book", invalidBook);
        model.addAttribute("violations", violations);
*/
        return "/violation-view";
    }
}
