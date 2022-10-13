package com.frontend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    private Long nik;

    private String name;

    private String email;
    
    private String address;

    private Long phone;

    private String username;

    private String password;

}