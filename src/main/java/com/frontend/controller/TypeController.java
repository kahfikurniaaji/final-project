package com.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frontend.model.Type;
import com.frontend.service.TypeService;

import lombok.AllArgsConstructor;

// Bukan controller utama

@Controller
@AllArgsConstructor
@RequestMapping("/type")
public class TypeController {
    
    private TypeService typeService;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("isActive","type");
        return "type/index";
    }
    
    @GetMapping("/create")
    public String create(Type type){
        return "type/create_form";
    }
        
    @PostMapping
    public String created(Type type){
        typeService.create(type);
        return "redirect:/type";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getById(id));
        return "type/update_form";
    }
    
    @PutMapping("/{id}")
    public String updated(@PathVariable Long id, Type type){
        typeService.update(id, type);
        return "redirect:/type";
    }
    
    @DeleteMapping("/{id}")
    public String deleted(@PathVariable Long id){
        typeService.delete(id);
        return "redirect:/type";
    }
    
}
