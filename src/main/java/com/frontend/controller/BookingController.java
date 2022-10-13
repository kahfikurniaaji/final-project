package com.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frontend.model.dto.request.BookingRequest;
import com.frontend.service.BookingService;
import com.frontend.service.CarService;
import com.frontend.service.ProfileService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    private ProfileService profileService;

    private CarService carService;
    
    @GetMapping
    public String index(Model model) {
 //       model.addAttribute("profile", profileService.getById(id));
        model.addAttribute("cars", carService.getAll());
        return "booking/booking";
    }

    @PostMapping
    public String created(BookingRequest bookingRequest) {
        bookingService.create(bookingRequest);
        return "redirect:/booking";
    }

}
