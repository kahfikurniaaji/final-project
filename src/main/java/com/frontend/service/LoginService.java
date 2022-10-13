package com.frontend.service;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.frontend.model.dto.request.LoginRequest;
import com.frontend.model.dto.response.LoginResponse;

@Service
public class LoginService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/login")
    private String url;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean login(LoginRequest loginRequest) {

        ResponseEntity<LoginResponse> respon = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(loginRequest),
                new ParameterizedTypeReference<LoginResponse>() {
                });

        if (respon.getStatusCode() == HttpStatus.OK) {
            System.out.println(respon.getBody());
            setAuthentication(respon.getBody(), loginRequest.getPassword());

            return true;
        }
        return false;
    }

    public void setAuthentication(LoginResponse res, String password) {
        Collection<GrantedAuthority> authorities = res.getAuthorities()
                .stream().map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                res.getUsername(),
                password,
                authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}