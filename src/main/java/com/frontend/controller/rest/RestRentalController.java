package com.frontend.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frontend.model.Rental;
import com.frontend.service.RentalService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rental")
public class RestRentalController {

    private RentalService rentalService;

    @GetMapping
    public List<Rental> getAll() {
        return rentalService.getAll();
    }

    @GetMapping("/{id}")
    public Rental getById(@PathVariable Long id) {
        return rentalService.getById(id);
    }

    @PostMapping
    public Rental create(@RequestBody Rental rental) {
        return rentalService.create(rental);
    }

    @PutMapping("/{id}")
    public Rental update(@PathVariable Long id, @RequestBody Rental rental) {
        return rentalService.update(id, rental);
    }

    @DeleteMapping("/{id}")
    public Rental delete(@PathVariable Long id) {
        return rentalService.delete(id);
    }

}
