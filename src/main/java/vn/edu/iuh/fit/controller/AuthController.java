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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public AuthController(UserService userService, OAuth2AuthorizedClientService authorizedClientService) {
        this.userService = userService;
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/admin")
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
}