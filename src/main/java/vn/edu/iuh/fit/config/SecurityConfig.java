package vn.edu.iuh.fit.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers( "/").permitAll(); // Cho phép các URL công khai
                    auth.anyRequest().authenticated(); // Các yêu cầu khác cần xác thực
                })
//                .oauth2Login(withDefaults()) // Cấu hình OAuth2 Login
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/api/auth/admin", true) // Chuyển hướng đến /admin sau khi đăng nhập thành công
//                        .failureUrl("/api/auth/home") // URL chuyển hướng nếu đăng nhập thất bại
                )
                .formLogin(withDefaults()) // Cấu hình Form Login
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
