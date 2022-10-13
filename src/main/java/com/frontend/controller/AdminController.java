package com.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frontend.model.dto.request.AdminRequest;
import com.frontend.service.AdminService;
import com.frontend.service.BookingService;
import com.frontend.service.BrandService;
import com.frontend.service.CarService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    private CarService carService;

    private BookingService bookingService;
    
    private BrandService brandService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cars", carService.getAll());
        model.addAttribute("brand", brandService.getAll());
    //    model.addAttribute("booking", bookingService.getAll());
        return "admin/admin";
    }

    @PostMapping
    public String created(AdminRequest admin) {
        adminService.create(admin);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("admin", adminService.getById(id));
        return "admin/update_form";
    }

    @PutMapping("/{id}")
    public String updated(@PathVariable Long id, AdminRequest admin) {
        adminService.update(id, admin);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleted(@PathVariable Long id) {
        adminService.delete(id);
        return "redirect:/admin";
    }

}
