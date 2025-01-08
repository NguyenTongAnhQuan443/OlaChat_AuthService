package vn.edu.iuh.fit.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.model.User;
import vn.edu.iuh.fit.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //    Find By Email
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    //    Login goolge
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
            return userRepository.save(newUser);
        }
    }

    //    Create user with email
    public User createUser(User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());

//        Encode password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encodedPassword);

        newUser.setCreatedAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    //    Update user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    //   Login with email
    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            User existingUser = user.get();
            if (passwordEncoder.matches(password, existingUser.getPassword())) {
                return existingUser;
            }
        }
        return null;
    }
}


