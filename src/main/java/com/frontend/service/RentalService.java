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

import com.frontend.model.Rental;

@Service
public class RentalService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/rental")
    private String url;

    @Autowired
    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Rental> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Rental>>() {
        }).getBody();
    }

    public Rental getById(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, null, new ParameterizedTypeReference<Rental>() {
        }).getBody();
    }

    public Rental create(Rental rental) {
        return restTemplate
                .exchange(url, HttpMethod.POST, new HttpEntity(rental), new ParameterizedTypeReference<Rental>() {
                }).getBody();
    }

    public Rental update(Long id, Rental rental) {
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity(rental),
                        new ParameterizedTypeReference<Rental>() {
                        })
                .getBody();
    }

    public Rental delete(Long id) {
        ResponseEntity<Rental> respon = restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Rental>() {
                });

        if (respon.getStatusCodeValue() == 200) {
            return respon.getBody();
        }

        return respon.getBody();
    }
}
