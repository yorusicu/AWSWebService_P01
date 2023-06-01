package com.aws.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// 둘다 사용 가능
//@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.formLogin();
        // Run > SecurityConfig > SecurityFilterChain > CustomSecurityConfigurer > init > configure > build
        http.apply(new CustomSecurityConfigurer().setFlag(true));

        return http.build();
    }
}
