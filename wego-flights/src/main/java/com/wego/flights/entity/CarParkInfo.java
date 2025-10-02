package com.wego.flights.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarParkInfo {

    @Id
    private String carParkNo;

    private String address;
    private String xCord;
    private String yCord;
    private String carParkType;
    private String typeOfParkingSystem;
    private String shortTermParking;
    private String freeParking;
    private String nightParking;
    private String carParkingDecks;
    private String gantryHeight;
    private String carParkBasement;
    private String totalLots;
    private String lotsType;
    private String lotsAvailable;
    private String updatedAt;

  }
