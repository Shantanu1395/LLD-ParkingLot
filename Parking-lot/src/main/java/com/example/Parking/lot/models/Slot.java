package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Slot {

    String id;
    SlotType slotType;
    SlotStatus slotStatus;
    Floor floor;
    Vehicle vehicle;

    public Slot(String id, SlotType slotType, SlotStatus slotStatus, Floor floor) {
        this.id = id;
        this.slotType = slotType;
        this.slotStatus = slotStatus;
        this.floor = floor;
    }

    public void assignVehicleToSlot(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void setSlotStatus(SlotStatus slotStatus){
        this.slotStatus = slotStatus;
    }

}
