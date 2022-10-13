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

import com.frontend.model.Car;
import com.frontend.model.dto.request.CarRequest;
import com.frontend.service.CarService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
public class RestCarController {

    private CarService carService;

    @GetMapping
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable Long id) {
        return carService.getById(id);
    }

    @PostMapping
    public Car create(@RequestBody CarRequest carRequest) {
        return carService.create(carRequest);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        return carService.update(id, carRequest);
    }

    @DeleteMapping("/{id}")
    public Car delete(@PathVariable Long id) {
        return carService.delete(id);
    }
}
