package com.frontend.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mitra {

    private Long id;

    private String nama;

    private String noHp;

    private String alamat;

    private List<Car> car;
}
