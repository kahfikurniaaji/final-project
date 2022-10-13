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

import com.frontend.model.Type;
import com.frontend.service.TypeService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/type")
public class RestTypeController {

    private TypeService typeService;

    @GetMapping
    public List<Type> getAll() {
        return typeService.getAll();
    }

    @GetMapping("/{id}")
    public Type getById(@PathVariable Long id) {
        return typeService.getById(id);
    }

    @PostMapping
    public Type create(@RequestBody Type type) {
        return typeService.create(type);
    }

    @PutMapping("/{id}")
    public Type update(@PathVariable Long id, @RequestBody Type type) {
        return typeService.update(id, type);
    }

    @DeleteMapping("/{id}")
    public Type delete(@PathVariable Long id) {
        return typeService.delete(id);
    }

}
