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

import com.frontend.model.dto.request.BookingRequest;

@Service
public class BookingService {
    
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/booking")
    private String url;

    @Autowired
    public BookingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BookingRequest> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookingRequest>>() {
        }).getBody();
    }

    public BookingRequest getById(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, null, new ParameterizedTypeReference<BookingRequest>() {
        }).getBody();
    }

    public BookingRequest create(BookingRequest bookingrequest) {
        return restTemplate
                .exchange(url, HttpMethod.POST, new HttpEntity(bookingrequest), new ParameterizedTypeReference<BookingRequest>() {
                }).getBody();
    }

    public BookingRequest update(Long id, BookingRequest bookingrequest) {
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity(bookingrequest),
                        new ParameterizedTypeReference<BookingRequest>() {
                        })
                .getBody();
    }

    public BookingRequest delete(Long id) {
        ResponseEntity<BookingRequest> respon = restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null,
                new ParameterizedTypeReference<BookingRequest>() {
                });

        if (respon.getStatusCodeValue() == 200) {
            return respon.getBody();
        }

        return respon.getBody();
    }

}
