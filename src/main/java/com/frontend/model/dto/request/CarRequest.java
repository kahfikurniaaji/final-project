/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author abiad
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    private String name;

    private Long cost;

    private String plat;

    private String gambar;

    private String typeName;
    
    private String brandName;   
    
}
