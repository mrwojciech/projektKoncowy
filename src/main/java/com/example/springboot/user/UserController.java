package com.example.springboot.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/add")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/add-view";
    }

    @PostMapping("/add")
    public String processRegistrationForm(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/add-view";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setActive(true);
        userRepository.save(user);
        return "redirect:/view/books/my-list";
    }
}
