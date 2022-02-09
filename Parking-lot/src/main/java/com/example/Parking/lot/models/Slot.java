package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Slot {

    private String id;
    private SlotType slotType;
    private SlotStatus slotStatus;
    private Floor floor;
    private Vehicle vehicle;

    public Slot(@NonNull final String slotID, @NonNull final SlotType slotType, @NonNull final SlotStatus slotStatus, @NonNull final Floor floor) {
        this.id = slotID;
        this.slotType = slotType;
        this.slotStatus = slotStatus;
        this.floor = floor;
    }

    public void assignVehicleToSlot(@NonNull final Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public void setSlotStatus(@NonNull final SlotStatus slotStatus){
        this.slotStatus = slotStatus;
    }

}
