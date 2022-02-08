package com.example.Parking.lot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Parking {
    String parkingId;
    String name;
    List<Gate> gates;
    List<Floor> floors;

    public Parking(String parkingId, String name) {
        this.parkingId = parkingId;
        this.name = name;
        this.gates = new ArrayList<>();
        this.floors = new ArrayList<>();
    }

    public void addGatesToParking(Gate gate){
        this.gates.add(gate);
    }

    public void addFloorsToParking(Floor floor){
        this.floors.add(floor);
    }

}
