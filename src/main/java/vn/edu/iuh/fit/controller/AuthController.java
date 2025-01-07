package vn.edu.iuh.fit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //    TEST
    @GetMapping("/admin")
    public ResponseEntity<Map<String, Object>> admin(OAuth2AuthenticationToken authentication) {
        Map<String, Object> response = new HashMap<>();
        try {
            OAuth2User user = authentication.getPrincipal();

            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getAttribute("sub"));
            userData.put("email", user.getAttribute("email"));
            userData.put("name", user.getAttribute("name"));
            userData.put("picture", user.getAttribute("picture"));

            response.put("code", "1");
            response.put("message", "Login successful");
            response.put("data", userData);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", "0");
            response.put("message", "Login failed");
            response.put("data", new HashMap<>());

            return ResponseEntity.status(401).body(response);
        }
    }
}