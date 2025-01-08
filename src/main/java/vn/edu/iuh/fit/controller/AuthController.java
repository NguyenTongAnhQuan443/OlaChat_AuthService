package vn.edu.iuh.fit.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import vn.edu.iuh.fit.model.User;
import vn.edu.iuh.fit.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public AuthController(UserService userService, OAuth2AuthorizedClientService authorizedClientService) {
        this.userService = userService;
        this.authorizedClientService = authorizedClientService;
    }

    //    Login with google
    @GetMapping("/login-success")
    public ResponseEntity<Map<String, Object>> admin(OAuth2AuthenticationToken authentication) {
        Map<String, Object> response = new HashMap<>();
        try {
            OAuth2User oAuth2User = authentication.getPrincipal();

            // Get access token
            OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                    authentication.getAuthorizedClientRegistrationId(),
                    authentication.getName()
            );

            String accessToken = client.getAccessToken().getTokenValue();

            // Process user login and save/update in database
            User dbUser = userService.processOAuthPostLogin(oAuth2User, accessToken);

            Map<String, Object> userData = new HashMap<>();
            userData.put("email", dbUser.getEmail());
            userData.put("given_name", dbUser.getFirstName());
            userData.put("family_name", dbUser.getLastName());
            userData.put("picture", dbUser.getProfilePicture());
            userData.put("access_token", dbUser.getAccessToken());

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

    //    create user with email
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User dbUser = userService.createUser(user);
            response.put("code", "1");
            response.put("message", "Register successful");
            response.put("data", dbUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", "0");
            response.put("message", "Register failed");
            response.put("data", new HashMap<>());
            return ResponseEntity.status(400).body(response);
        }
    }

    //    Login with email
    @PostMapping("/login-email")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User dbUser = userService.loginUser(user.getEmail(), user.getPassword());
            if (dbUser != null) {
                response.put("code", "1");
                response.put("message", "Login successful");
                response.put("data", dbUser);
                return ResponseEntity.ok(response);
            } else {
                response.put("code", "0");
                response.put("message", "Invalid email or password");
                response.put("data", new HashMap<>());
                return ResponseEntity.status(401).body(response);
            }
        } catch (Exception e) {
            response.put("code", "0");
            response.put("message", "Login failed");
            response.put("data", new HashMap<>());
            return ResponseEntity.status(400).body(response);
        }
    }

    //    Update user
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            User dbUser = userService.updateUser(user);
            response.put("code", "1");
            response.put("message", "Update successful");
            response.put("data", dbUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", "0");
            response.put("message", "Update failed");
            response.put("data", new HashMap<>());
            return ResponseEntity.status(400).body(response);
        }
    }

    //    Get postCount for user
    @GetMapping("/post-count/{email}")
    public ResponseEntity<Map<String, Object>> getPostsCount(@PathVariable String email) {

        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> user = userService.findByEmail(email);
            response.put("code", "1");
            response.put("message", "Get post count successful");
            response.put("postsCount", user.get().getPostsCount());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("code", "0");
            response.put("message", "Get post count failed");
            response.put("data", new HashMap<>());
            return ResponseEntity.status(400).body(response);
        }
    }
}