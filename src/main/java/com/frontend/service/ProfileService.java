package com.frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.frontend.model.Profile;
import com.frontend.model.dto.request.ProfileRequest;

@Service
public class ProfileService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/profile")
    private String url;

    @Autowired
    public ProfileService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Profile> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Profile>>() {
        }).getBody();
    }

    public Profile getById(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, null, new ParameterizedTypeReference<Profile>() {
        }).getBody();
    }

    public Profile create(ProfileRequest profileRequest) {
        return restTemplate
                .exchange(url, HttpMethod.POST, new HttpEntity(profileRequest), new ParameterizedTypeReference<Profile>() {
                }).getBody();
    }

    public Profile update(Long id, Profile profile) {
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity(profile),
                        new ParameterizedTypeReference<Profile>() {
                        })
                .getBody();
    }

    public Profile delete(Long id) {
        ResponseEntity<Profile> respon = restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Profile>() {
                });

        if (respon.getStatusCodeValue() == 200) {
            return respon.getBody();
        }

        return respon.getBody();
    }
}
