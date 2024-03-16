package com.example.codemaster.service;

import com.example.codemaster.controller.AuthController;
import com.example.codemaster.exception.BusinessException;
import com.example.codemaster.exception.BusinessExceptionCode;
import com.example.codemaster.model.DTO.RegisterRequestDTO;
import com.example.codemaster.model.Role;
import com.example.codemaster.model.User;
import com.example.codemaster.repository.RoleRepository;
import com.example.codemaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserDetailsImplementationService implements UserDetailsService {

    @Value("${security.decipherKey}")
    private String key;

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final RoleRepository roleRepository;


    @Autowired
    public UserDetailsImplementationService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImplementation.build(user);
    }

    public void registerUser(RegisterRequestDTO registerRequest) throws BusinessException {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new BusinessException(BusinessExceptionCode.USERNAME_ALREADY_REGISTERED);
        }

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new BusinessException(BusinessExceptionCode.EMAIL_ALREADY_REGISTERED);
        }
        String decryptedPassword = AuthController.decrypt(registerRequest.getPassword(), key);

        Role role = roleRepository.findByName(registerRequest.getRole()).orElseThrow(() -> new BusinessException(BusinessExceptionCode.INVALID_USER_FORMAT));

        User userToSave = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(decryptedPassword))
                .roles(Set.of(role))
                .firstLogin(true)
                .build();

        userRepository.save(userToSave);
    }

}
