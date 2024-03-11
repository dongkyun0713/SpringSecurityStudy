package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Security 필터 체인을 설정하는 Bean을 생성하는 메서드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );
        http
                // 폼 기반 로그인 설정
                .formLogin((auth) -> auth.loginPage("/login")  // 로그인 페이지를 "/login"으로 지정
                        .loginProcessingUrl("/loginProc")  // 로그인 처리 URL을 "/loginProc"으로 지정
                        .permitAll()  // 로그인 페이지 및 로그인 처리 URL은 누구나 접근 가능
                );
        http
                // CSRF 보호 비활성화
                // 나중에 다시 활성화 할 예정
                .csrf((auth) -> auth.disable());
        return http.build();
    }
}
