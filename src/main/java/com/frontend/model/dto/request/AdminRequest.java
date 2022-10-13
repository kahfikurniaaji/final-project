package com.frontend.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {

    private String carName;

    private Long cost;

    private String plat;

    private String typeName;

    private String brandName;

    private String image;

}
