package com.frontend.service;

import org.springframework.web.client.RestTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.frontend.model.Car;
import com.frontend.model.dto.request.CarRequest;

@Service
public class CarService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/car")
    private String url;

    @Autowired
    public CarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Car> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
        }).getBody();
    }

    public Car getById(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, null, new ParameterizedTypeReference<Car>() {
        }).getBody();
    }

    public Car create(CarRequest carRequest) {
        return restTemplate
                .exchange(url, HttpMethod.POST, new HttpEntity(carRequest), new ParameterizedTypeReference<Car>() {
                }).getBody();
    }

    public Car update(Long id, CarRequest carRequest) {
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity(carRequest),
                        new ParameterizedTypeReference<Car>() {
                        })
                .getBody();
    }

    public Car delete(Long id) {
        ResponseEntity<Car> respon = restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Car>() {
                });

        if (respon.getStatusCodeValue() == 200) {
            return respon.getBody();
        }

        return respon.getBody();
    }
}
