package com.example.userservice.controllers;


import com.example.userservice.dtos.UserRequestDTO;
import com.example.userservice.dtos.UserResponseDTO;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//origin local host 3000
@RestController
@RequestMapping(value = "/users")

public class UserController {

    private final UserService userService;
    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }
     
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDTO> getPersons() {
        return userService.findUsers();
    }
     
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> getPerson(@PathVariable("id") Long userId) {
        UserResponseDTO dto = userService.findUserById(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> getPersonbyEmailAndPassword(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO dto = userService.findUserByEmailAndPassword(userRequestDTO.getEmail(), userRequestDTO.getPassword());
        if (dto == null) {
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<UserResponseDTO> getPersonbyEmail(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO dto = userService.findUserByEmail(userRequestDTO.getEmail());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
     
    @PostMapping(value = "/register" , consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> insertUser(@RequestBody UserRequestDTO userRequestDTO) {
        System.out.println(userRequestDTO);
        UserResponseDTO userResponseDTO = userService.insert(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }
     
    @PutMapping(value = "/update" , consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.update(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
     
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.delete(userId);
        String message = "User deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
