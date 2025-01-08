package vn.edu.iuh.fit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login-email").permitAll() // Cho phép các endpoint này công khai
                        .anyRequest().authenticated() // Yêu cầu xác thực với các endpoint khác
                )
                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF (nếu cần)
                .formLogin(AbstractHttpConfigurer::disable) // Tắt Form Login mặc định
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/api/auth/login-success", true) // Cấu hình OAuth2
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
