package com.example.springboot.admin;

import com.example.springboot.user.User;
import com.example.springboot.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserViewController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserViewController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/adminLandingPage")
    public String adminLandingPage(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/users/list-view";
    }

}
