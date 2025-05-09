package com.leagueplay.leagueplay.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

import com.leagueplay.leagueplay.models.User;
import com.leagueplay.leagueplay.dto.RegisterDTO;
import com.leagueplay.leagueplay.repositories.UserRepository;
import com.leagueplay.leagueplay.dto.LoginDTO;


@Service
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
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
    //Bucar por usuario o correo y compararlo para verificar que existe
    public void loginUser(LoginDTO loginDTO){
        String identifier = loginDTO.getIdetenfier();
        String password = loginDTO.getPassword();
        Optional<User> userOptional;

        if(identifier.contains("@")){
            userOptional = userRepository.findByEmail(identifier);
        }else{
            userOptional = userRepository.findByUsername(identifier);
        }
        User user = userOptional.orElseThrow(()-> 
        new RuntimeException("Usuario no encontrado")
        );
    
    //Comparar contraseñas

    if(!passwordEncoder.matches(password, user.getPassword())){
        throw new RuntimeException("Contraseña incorrecta");

    }
    
    System.out.println("login exitoso de: " + user.getUsername());
    }


}
