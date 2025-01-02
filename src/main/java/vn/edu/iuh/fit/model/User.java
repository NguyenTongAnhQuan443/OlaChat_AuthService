package vn.edu.iuh.fit.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private String gender;
    private String bio;
    private String profilePicture;
    private String province;
    private String district;
    private String ward;

    private int followersCount;
    private int followingCount;
    private int postsCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

}