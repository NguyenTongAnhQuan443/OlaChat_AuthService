package vn.edu.iuh.fit.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.model.User;
import vn.edu.iuh.fit.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    @ResponseStatus(HttpStatus.CREATED)
//    public User register(@RequestBody User user) {
//        return userService.register(user);
//    }
//
//    @PostMapping("/login")
//    public User login(@RequestBody LoginRequest loginRequest) {
//        return userService.login(loginRequest.getEmail(), loginRequest.getPassword())
//                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
//    }
}