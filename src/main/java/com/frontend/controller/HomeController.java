package com.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // HTML dan JSON    Randika, Kurniawan, Alfian, Andi
public class HomeController {

    @GetMapping("/dashboard")
    public String index(){
        return "index";
    }
    
    
}