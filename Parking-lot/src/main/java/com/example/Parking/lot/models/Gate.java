package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.GateType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Gate {
    private String gateId;
    private String name;
    private GateType gateType;
    private Parking parking;
}
