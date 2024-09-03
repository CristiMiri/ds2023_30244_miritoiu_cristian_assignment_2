package com.example.userservice.config;

import com.example.userservice.entities.User;
import com.example.userservice.entities.UserRole;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, UserService userService) {
        if (userRepository.count() != 0) {
            return args -> {
            };
        }
        return args -> {
            User defaultAdmin= User.builder()
                    .id(1L)
                    .username("admin")
                    .password("admin")
                    .email("admin@gmail.com")
                    .FirstName("admin")
                    .LastName("Daniel")
                    .role(UserRole.ADMIN)
                    .build();
            User defaultUser = User.builder()
                    .id(2L)
                    .username("user")
                    .password("user")
                    .email("user@user.com")
                    .FirstName("user")
                    .LastName("Ismail")
                    .role(UserRole.USER)
                    .build();
            userRepository.save(defaultAdmin);
            userRepository.save(defaultUser);
            System.out.println("This is default admin");
            System.out.println(defaultAdmin);
            System.out.println("This is default user");
            System.out.println(defaultUser);
        };
        }
}
