package com.frontend.model.dto.request;

import java.util.Date;

import com.frontend.model.Car;
import com.frontend.model.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    
    private Profile profile;

    private Car car;

    private Date rentalDate;

    private Date returnDate;

    private Long rentCost;

    private String status;

}
