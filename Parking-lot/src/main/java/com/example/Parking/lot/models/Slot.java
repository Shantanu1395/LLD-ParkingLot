package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Slot {

    String id;
    SlotType slotType;
    SlotStatus slotStatus;
    Floor floor;

}
