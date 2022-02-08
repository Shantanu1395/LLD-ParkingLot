package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.GateType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Gate {
    String gateId;
    String name;
    GateType gateType;
    Parking parking;
}
