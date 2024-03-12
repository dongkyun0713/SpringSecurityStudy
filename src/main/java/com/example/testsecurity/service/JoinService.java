package com.example.testsecurity.service;

import com.example.testsecurity.Entity.User;
import com.example.testsecurity.dto.JoinDto;
import com.example.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void joinProcess(JoinDto joinDto) {
        User user = new User();

        boolean isUser = userRepository.existsUserByUserName(user.getUserName());
        if (isUser) return;

        user.setUserName(joinDto.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));  // 암호화 시켜서 데베에 저장되어야 함.
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
