package com.example.userservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "username", nullable = false,unique = true, columnDefinition = "TEXT")
    private String username;

    @Column(name = "password", nullable = false ,columnDefinition = "TEXT")
    private String password;

    @Column(name = "email", nullable = false,unique = true, columnDefinition = "TEXT")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false )
    private UserRole role;

    @Column(name = "firstName", nullable = false, columnDefinition = "TEXT")
    private String FirstName;

    @Column(name = "lastName", nullable = false, columnDefinition = "TEXT")
    private String LastName;

    public User() {
    }



}
