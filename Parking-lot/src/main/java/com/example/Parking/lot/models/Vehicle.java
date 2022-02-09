package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.SlotType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Vehicle {

    private String vehicleNo;
    private SlotType vehicleType;
    private Slot slot;

    private List<Ticket> trips;

    public void assignSlot(@NonNull final Slot slot){
        this.slot = slot;
    }

    public Vehicle(@NonNull final String vehicleNo, @NonNull final SlotType vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.trips = new ArrayList<>();
    }

    public void addTrip(@NonNull final Ticket ticket){
        this.trips.add(ticket);
    }

}
