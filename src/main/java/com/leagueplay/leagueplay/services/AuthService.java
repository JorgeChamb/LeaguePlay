package com.leagueplay.leagueplay.services;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.leagueplay.leagueplay.dto.RegisterDTO;
import com.leagueplay.leagueplay.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.leagueplay.leagueplay.models.User;



@Service

public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository){
        this.userRepository= userRepository;
    }
    
    public void registerUser(RegisterDTO registerDTO){
        String hashedpassword = passwordEncoder.encode(registerDTO.getPassword());

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setEnabled(false);
        user.setPassword(hashedpassword);
        user.setCreateAt(LocalDateTime.now());
        userRepository.save(user);

    }
   
    

    


}
