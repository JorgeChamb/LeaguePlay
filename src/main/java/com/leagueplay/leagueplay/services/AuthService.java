package com.leagueplay.leagueplay.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.leagueplay.leagueplay.models.User;
import com.leagueplay.leagueplay.dto.RegisterDTO;
import com.leagueplay.leagueplay.repositories.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterDTO registerDTO) {
        String hashedPassword = passwordEncoder.encode(registerDTO.getPassword());

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(hashedPassword);
        user.setEnabled(false);
        user.setCreateAt(LocalDateTime.now());

        System.out.println("Registrando usuario: " + user.getUsername() + " - Email: " + user.getEmail());

        userRepository.save(user);
    }
}
