package com.example.Parking.lot.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Floor {

    String id;
    String floorName;
    Parking parking;
    List<Slot> slots;

    public Floor(String id, String floorName, Parking parking) {
        this.id = id;
        this.floorName = floorName;
        this.parking = parking;
        this.slots = new ArrayList<>();
    }

    public void addSlotsToFloor(Slot slot){
        this.slots.add(slot);
    }

}
