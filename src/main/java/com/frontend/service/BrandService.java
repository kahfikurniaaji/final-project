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

import com.frontend.model.Brand;

@Service
public class BrandService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/brand")
    private String url;

    @Autowired
    public BrandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Brand> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Brand>>() {
        }).getBody();
    }

    public Brand getById(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, null, new ParameterizedTypeReference<Brand>() {
        }).getBody();
    }

    public Brand create(Brand brand) {
        return restTemplate
                .exchange(url, HttpMethod.POST, new HttpEntity(brand), new ParameterizedTypeReference<Brand>() {
                }).getBody();
    }

    public Brand update(Long id, Brand brand) {
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity(brand),
                        new ParameterizedTypeReference<Brand>() {
                        })
                .getBody();
    }

    public Brand delete(Long id) {
        ResponseEntity<Brand> respon = restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Brand>() {
                });

        if (respon.getStatusCodeValue() == 200) {
            return respon.getBody();
        }

        return respon.getBody();
    }
}
