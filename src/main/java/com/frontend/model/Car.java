package com.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Long id;

    private String name;

    private Long cost;

    private String plat;

    private Type type;

    private Brand brand;

    private String gambar;
    
}
