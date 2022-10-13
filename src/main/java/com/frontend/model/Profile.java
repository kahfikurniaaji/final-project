package com.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private Long id;

    private Long nik;

    private String name;

    private String email;

    private String address;

    private String phone;

    private User user;

}