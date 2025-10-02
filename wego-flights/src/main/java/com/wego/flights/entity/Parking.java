package com.wego.flights.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Parking {
    private String address;
    private double latitude;
    private double longitude;
    private int total_lots;
    private int available_lots;
}
