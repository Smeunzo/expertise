package com.commerce.services.auth.service;

import com.commerce.services.auth.dao.AuthRepository;
import com.commerce.services.auth.entity.AuthCredentials;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<AuthCredentials> loadUsers(){
        return userRepository.findAll();
    }

    public AuthCredentials loadUserByUsername(String username){
        return userRepository.findUserCredentialsByUsername(username);
    }

    public AuthCredentials addUser(AuthCredentials authCredentials){
        if(userRepository.existsByUsername(authCredentials.getUsername()))
            throw new RuntimeException("Username already exists");

        String password = authCredentials.getPassword();
        authCredentials.setPassword(passwordEncoder.encode(password));
        return userRepository.save(authCredentials);
    }
}
