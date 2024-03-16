package com.example.codemaster.model.DTO;


import com.example.codemaster.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private ERole role;
}
