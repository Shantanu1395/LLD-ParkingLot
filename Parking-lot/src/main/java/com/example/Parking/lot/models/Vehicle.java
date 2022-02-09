package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.SlotType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Vehicle {
    String vehicleNo;
    SlotType vehicleType;
    Slot slot;

    List<Ticket> trips;

    public void assignSlot(Slot slot){
        this.slot = slot;
    }

    public Vehicle(String vehicleNo, SlotType vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.trips = new ArrayList<>();
    }

    public void addTrip(Ticket ticket){
        this.trips.add(ticket);
    }

}
