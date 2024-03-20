package com.example.testsecurity.service;

import com.example.testsecurity.Entity.User;
import com.example.testsecurity.dto.CustomUserDetails;
import com.example.testsecurity.repository.UserRepository;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);

        if (user != null) return new CustomUserDetails(user);

        return null;
    }

}
