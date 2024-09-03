package com.example.deviceservice.controllers;

import com.example.deviceservice.entities.User;
import com.example.deviceservice.services.DeviceService;
import com.example.deviceservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device_users")
public class UserController {
    private final UserService userService;
    private final DeviceService deviceService;

    @Autowired
    public UserController(UserService userService, DeviceService deviceService) {
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping(value = "/register" , consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> insertUser(@RequestBody User userRequestDTO) {
        User User = userService.insert(userRequestDTO);
        return new ResponseEntity<>(User, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update" , consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User User = userService.update(user);
        return new ResponseEntity<>(User, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        deviceService.deleteByUserId(userId);
        //No longer nee ded because of the cascade delete
        User User = userService.findById(userId);
        System.out.println(User);
        if (User != null) {
            userService.delete(userId);
        }
        String message = "User deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
