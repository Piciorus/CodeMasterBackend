package com.example.codemaster.controller;

import com.example.codemaster.exception.BusinessException;
import com.example.codemaster.model.DTO.LoginRequestDTO;
import com.example.codemaster.model.DTO.RegisterRequestDTO;
import com.example.codemaster.service.UserDetailsImplementationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthController authenticator;

    @Autowired
    UserDetailsImplementationService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequest) throws BusinessException {
        this.userDetailsService.registerUser(registerRequest);
        return this.authenticator.authenticateUser(new LoginRequestDTO(registerRequest.getUsername(), registerRequest.getPassword()));
    }

}