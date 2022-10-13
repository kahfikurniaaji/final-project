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

import com.frontend.model.Profile;
import com.frontend.model.dto.request.ProfileRequest;
import com.frontend.service.ProfileService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/profile")
public class RestProfileController {

    private ProfileService profileService;

    @GetMapping
    public List<Profile> getAll() {
        return profileService.getAll();
    }

    @GetMapping("/{id}")
    public Profile getById(@PathVariable Long id) {
        return profileService.getById(id);
    }

    @PostMapping
    public Profile create(@RequestBody ProfileRequest profilerRequest) {
        return profileService.create(profilerRequest);
    }

    @PutMapping("/{id}")
    public Profile update(@PathVariable Long id, @RequestBody Profile profile) {
        return profileService.update(id, profile);
    }

    @DeleteMapping("/{id}")
    public Profile delete(@PathVariable Long id) {
        return profileService.delete(id);
    }

}
