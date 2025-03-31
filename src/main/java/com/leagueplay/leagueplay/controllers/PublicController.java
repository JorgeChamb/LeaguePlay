package com.leagueplay.leagueplay.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/publica")

public class PublicController {
    
    @GetMapping("/principal")
    public String getInfo(){
        return "!Bienvenidos a LeaguePlay¡ Encuentra compañeros para jugar.";
    }
    
}
