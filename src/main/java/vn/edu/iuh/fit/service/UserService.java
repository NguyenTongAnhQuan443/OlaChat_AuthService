package vn.edu.iuh.fit.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import vn.edu.iuh.fit.model.User;
import vn.edu.iuh.fit.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    public User register(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public Optional<User> login(String email, String rawPassword) {
//        Optional<User> user = userRepository.findUserByEmail(email);
//        if (user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword())) {
//            user.get().setLastLogin(LocalDateTime.now());
//            userRepository.save(user.get());
//            return user;
//        }
//        return Optional.empty();
//    }
}

