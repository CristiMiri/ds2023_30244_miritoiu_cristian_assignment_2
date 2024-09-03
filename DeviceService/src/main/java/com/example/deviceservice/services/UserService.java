package com.example.deviceservice.services;

import com.example.deviceservice.entities.User;
import com.example.deviceservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insert(User newUser) {
        System.out.println(newUser);
        userRepository.save(newUser);
        return newUser;
    }

    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    public User delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        userRepository.delete(user);
        return user;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        }
        return null;
    }
}
