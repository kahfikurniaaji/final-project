package com.frontend.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    private Long id;

    private Profile profile;
    
    private Car car;

    private Date rentalDate;

    private Date returnDate;

    private Long rentCost;

    private String status;

}
