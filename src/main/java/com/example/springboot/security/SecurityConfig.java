package com.example.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .antMatchers("/view/user/add").hasRole("ADMIN")
                .antMatchers("/view/book/add", "/view/book/update", "/view/book/delete").hasRole("ADMIN")
                .antMatchers("/view/author/add", "/view/author/update", "/view/author/delete").hasRole("ADMIN")
                .antMatchers("/view/publisher/add", "/view/publisher/update", "/view/publisher/delete", "/view/trainee/add").hasRole("ADMIN")
                .antMatchers("/view/book/list", "/books/byRating", "/view/trainee/list").permitAll()
                .antMatchers("favicon.ico", "/styles.css").permitAll()

                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
           //     .successHandler(myCustomAuthenticationSuccessHandler()) // Use the custom success handler
                .defaultSuccessUrl("/defaultLanding")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .build();
    }

}
