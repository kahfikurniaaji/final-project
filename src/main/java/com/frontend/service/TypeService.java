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

import com.frontend.model.Type;

@Service
public class TypeService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/type")
    private String url;

    @Autowired
    public TypeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Type> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Type>>() {
        }).getBody();
    }

    public Type getById(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, null, new ParameterizedTypeReference<Type>() {
        }).getBody();
    }

    public Type create(Type type) {
        return restTemplate
                .exchange(url, HttpMethod.POST, new HttpEntity(type), new ParameterizedTypeReference<Type>() {
                }).getBody();
    }

    public Type update(Long id, Type type) {
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity(type),
                        new ParameterizedTypeReference<Type>() {
                        })
                .getBody();
    }

    public Type delete(Long id) {
        ResponseEntity<Type> respon = restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Type>() {
                });

        if (respon.getStatusCodeValue() == 200) {
            return respon.getBody();
        }

        return respon.getBody();
    }
}
