package com.example.springboot.security;

import com.example.springboot.user.User;
import com.example.springboot.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No username " + username + " found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getActive(), true, true, true,
                Set.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
