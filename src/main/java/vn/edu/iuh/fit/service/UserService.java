package vn.edu.iuh.fit.service;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.model.User;
import vn.edu.iuh.fit.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User processOAuthPostLogin(OAuth2User oAuth2User, String accessToken) {
        String email = oAuth2User.getAttribute("email");

        Optional<User> existingUser = userRepository.findUserByEmail(email);

        if (existingUser.isPresent()) {
            // Update access token for existing user
            User user = existingUser.get();
            user.setAccessToken(accessToken); // Update token
            return userRepository.save(user);
        } else {
            // Create new user
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFirstName(oAuth2User.getAttribute("given_name"));
            newUser.setLastName(oAuth2User.getAttribute("family_name"));
            newUser.setProfilePicture(oAuth2User.getAttribute("picture"));
            newUser.setAccessToken(accessToken); // Set token
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(newUser);
        }
    }
}


