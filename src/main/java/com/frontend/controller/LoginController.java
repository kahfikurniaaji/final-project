package com.frontend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frontend.model.dto.request.LoginRequest;
import com.frontend.service.LoginService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @GetMapping
    public String loginView(LoginRequest loginRequest, Authentication authentication) {
        System.out.println(authentication);
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "auth/login";
        }
        return "redirect:/";
        
    }

    @PostMapping
    public String login(LoginRequest loginRequest) {
        if (!loginService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        List<String> roles = authentication.getAuthorities()
                .stream().map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        for (String role : roles) {
            if (role.equals("ROLE_USER")) {
                return "rdirect:/dashboard";
            }
            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin";
            }
        }

        return "redirect:/";
    }

}
