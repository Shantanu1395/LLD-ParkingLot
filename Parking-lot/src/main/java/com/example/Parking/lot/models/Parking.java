package com.example.Parking.lot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Parking {

    private String parkingId;
    private String name;
    private List<Gate> gates;
    private List<Floor> floors;

    public Parking(@NonNull final String parkingId, @NonNull final String name) {
        this.parkingId = parkingId;
        this.name = name;
        this.gates = new ArrayList<>();
        this.floors = new ArrayList<>();
    }

    public void addGatesToParking(@NonNull final Gate gate){
        this.gates.add(gate);
    }

    public void addFloorsToParking(@NonNull final Floor floor){
        this.floors.add(floor);
    }

}
