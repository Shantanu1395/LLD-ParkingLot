package com.example.Parking.lot.controllers;

import com.example.Parking.lot.enums.GateType;
import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import com.example.Parking.lot.models.Parking;
import com.example.Parking.lot.services.ParkingService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class ParkingController {

    private ParkingService parkingService;

    public String createParking(@NonNull final String name){
        return parkingService.createParking(name).getParkingId();
    }

    public String createGate(@NonNull final String gateName,@NonNull final  GateType gateType,@NonNull final  String parkingId){
        return parkingService.createGate(gateName, gateType, parkingId).getGateId();
    }

    public String createFloor(@NonNull final String floorName,@NonNull final String parkingId){
        return parkingService.createFloor(floorName, parkingId).getId();
    }

    public String createSlot(@NonNull final SlotType slotType,@NonNull final String floorId){
        return parkingService.createSlot(slotType, floorId).getId();
    }

    public List<Parking> getParkingList(){
        return this.parkingService.getParkings();
    }

    public String registerVehicle(@NonNull final String vehicleNumber,@NonNull final SlotType vehicleType){
        parkingService.registerVehicle(vehicleNumber, vehicleType);
        return vehicleNumber;
    }
}
