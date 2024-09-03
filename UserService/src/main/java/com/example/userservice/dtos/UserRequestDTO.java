package com.example.userservice.dtos;

import com.example.userservice.entities.UserRole;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
}
