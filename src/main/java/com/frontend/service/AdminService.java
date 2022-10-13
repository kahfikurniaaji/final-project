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

import com.frontend.model.dto.request.AdminRequest;

@Service
public class AdminService {
    
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/admin")
    private String url;

    @Autowired
    public AdminService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<AdminRequest> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<AdminRequest>>() {
        }).getBody();
    }

    public AdminRequest getById(Long id) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, null, new ParameterizedTypeReference<AdminRequest>() {
        }).getBody();
    }

    public AdminRequest create(AdminRequest adminrequest) {
        return restTemplate
                .exchange(url, HttpMethod.POST, new HttpEntity(adminrequest), new ParameterizedTypeReference<AdminRequest>() {
                }).getBody();
    }

    public AdminRequest update(Long id, AdminRequest adminrequest) {
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity(adminrequest),
                        new ParameterizedTypeReference<AdminRequest>() {
                        })
                .getBody();
    }

    public AdminRequest delete(Long id) {
        ResponseEntity<AdminRequest> respon = restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, null,
                new ParameterizedTypeReference<AdminRequest>() {
                });

        if (respon.getStatusCodeValue() == 200) {
            return respon.getBody();
        }

        return respon.getBody();
    }

}
