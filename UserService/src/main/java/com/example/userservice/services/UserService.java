package com.example.userservice.services;


import com.example.userservice.dtos.UserRequestDTO;
import com.example.userservice.dtos.UserResponseDTO;
import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private final WebClient webClient ;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
//      String baseUrl="http://localhost:8081";
        String baseUrl = "http://device-service:8081";
        webClient = WebClient.create(baseUrl);
        System.out.println("base url: " + baseUrl);
    }

    public List<UserResponseDTO> findUsers() {
        List<User> usersDTOList = userRepository.findAll();
        return usersDTOList.stream()
                .map(this::mapToUserResponseDTO)
                .collect(Collectors.toList());
    }
//update

    public UserResponseDTO findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        return mapToUserResponseDTO(user);
    }


    public UserResponseDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return mapToUserResponseDTO(user);
    }

    public UserResponseDTO insert(UserRequestDTO userRequestDTO) {
        User user = mapToUser(userRequestDTO);
//        System.out.println(user);
        user = userRepository.save(user);
        user = userRepository.findByUsername(userRequestDTO.getUsername());
        String response = webClient.post()
//                http://localhost:8081/device_users/register
                .uri("/device_users/register")
                .body(Mono.just(user), User.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    System.out.println(clientResponse.statusCode());
                    return Mono.error(new RuntimeException());
                })
                .bodyToMono(String.class)
                .block();

        System.out.printf("response: %s%n", response);
        return mapToUserResponseDTO(user);
    }

    public UserResponseDTO update(UserRequestDTO userRequestDTO) {
        User user = mapToUser(userRequestDTO);
        User berforeUpdate = userRepository.findByUsername(userRequestDTO.getUsername());
        user.setId(berforeUpdate.getId());
        if (user.getPassword()=="*****")
            user.setPassword(berforeUpdate.getPassword());
        else
            user.setPassword(user.getPassword());
        if (user.getPassword() == null)
            user.setPassword(berforeUpdate.getPassword());

        user = userRepository.save(user);
        webClient
                .put()
                .uri("/device_users/update")
                .body(Mono.just(user), User.class)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    System.out.println(clientResponse.statusCode());
                    return Mono.error(new RuntimeException());
                })
                .bodyToMono(Void.class)
                .block(); // blocking call, use subscribe() in a reactive application
        return mapToUserResponseDTO(user);
    }

    public UserResponseDTO delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
//        http://localhost:8081/device_users/delete/{{id}}
        webClient
                .delete()
                .uri("/device_users/delete/"+user.getId())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    System.out.println(clientResponse.statusCode());
                    return Mono.error(new RuntimeException());
                })
                .bodyToMono(Void.class)
                .block(); // blocking call, use subscribe() in a reactive application

        System.out.println("DELETE request successful");
        userRepository.delete(user);
        return mapToUserResponseDTO(user);
    }

    public UserResponseDTO findUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            return null;
        }
        return mapToUserResponseDTO(user);
    }

    private UserResponseDTO mapToUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
    }
    private User mapToUser(UserRequestDTO userRequestDTO) {
        return User.builder()
//                .id(userRequestDTO.getId())
                .username(userRequestDTO.getUsername())
//                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .password(userRequestDTO.getPassword())
                .FirstName(userRequestDTO.getFirstName())
                .LastName(userRequestDTO.getLastName())
                .email(userRequestDTO.getEmail())
                .role(userRequestDTO.getRole())
                .build();
    }
}
