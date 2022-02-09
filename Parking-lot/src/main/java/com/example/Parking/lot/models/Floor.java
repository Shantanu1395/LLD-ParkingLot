package com.example.Parking.lot.models;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Floor {

    private String id;
    private String floorName;
    private Parking parking;
    private List<Slot> slots;

    public Floor(@NonNull final String id,@NonNull final String floorName, @NonNull final Parking parking) {
        this.id = id;
        this.floorName = floorName;
        this.parking = parking;
        this.slots = new ArrayList<>();
    }

    public void addSlotsToFloor(@NonNull final Slot slot){
        this.slots.add(slot);
    }

}
