package com.project.gestione_eventi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {

    @GetMapping("home")
    public String getHomme() {
        return "Questa è l'homepage";
    }
    
    
}
