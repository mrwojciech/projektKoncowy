package com.example.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .antMatchers("/view/user/add").permitAll()
                .antMatchers("/view/book/add", "/view/book/update", "/view/book/delete").hasRole("ADMIN")
                .antMatchers("/view/author/add", "/view/author/update", "/view/author/delete").hasRole("ADMIN")
                .antMatchers("/view/publisher/add", "/view/publisher/update", "/view/publisher/delete", "/view/trainee/add").hasRole("ADMIN")
                .antMatchers("/view/book/list", "/books/byRating", "/view/trainee/list").permitAll()
                .antMatchers("favicon.ico").permitAll()
                .antMatchers("styles.css").permitAll()

                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/view/trainee/add")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/view/book/list")
                .and()
                .build();
    }
}
