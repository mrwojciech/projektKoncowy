package com.example.springboot.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginView() {
        return "/login-view";
    }

//    Potrzebne tylko przy włączonej obsłudze CSRF w SecurityConfig
//    Wtedy login może być realizowany tylko z wykorzystaniem żądań typu POST, a nie GET
//    @GetMapping("/logout")
//    public String getLogoutView() {
//        return "/logout-view";
//    }
}
