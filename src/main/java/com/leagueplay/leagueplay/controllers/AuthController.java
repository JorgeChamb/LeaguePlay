package com.leagueplay.leagueplay.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.leagueplay.leagueplay.dto.RegisterDTO;
import com.leagueplay.leagueplay.services.AuthService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor    
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterDTO registerDTO){
        authService.registerUser(registerDTO);
        return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.CREATED);

    }
   
    

    
    
}
