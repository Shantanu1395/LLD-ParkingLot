package com.example.Parking.lot.controllers;

import com.example.Parking.lot.enums.GateType;
import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import com.example.Parking.lot.models.Parking;
import com.example.Parking.lot.services.ParkingService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ParkingController {

    ParkingService parkingService;

    public String createParking(String name){
        return this.parkingService.createParking(name).getParkingId();
    }

    public String createGate(String gateName, GateType gateType, String parkingId){
        return this.parkingService.createGate(gateName, gateType, parkingId).getGateId();
    }

    public String createFloor(String floorName, String parkingId){
        return this.parkingService.createFloor(floorName, parkingId).getId();
    }

    public String createSlot(SlotType slotType, SlotStatus slotStatus, String floorId){
        return this.parkingService.createSlot(slotType, slotStatus, floorId).getId();
    }

    public void addGateToParking(String parkingId, String gateId){
        this.parkingService.addGateToParking(parkingId, gateId);
    }

    public void addFloorToParking(String parkingId, String floorId){
        this.parkingService.addFloorToParking(parkingId, floorId);
    }

    public void addSlotToFloor(String slotId, String floorId){
        this.parkingService.addSlotToFloor(slotId, floorId);
    }

    public List<Parking> getParkings(){
        return this.parkingService.getParkings();
    }

    public String registerVehicle(String vehicleNumber, SlotType vehicleType){
        this.parkingService.registerVehicle(vehicleNumber, vehicleType);
        return vehicleNumber;
    }
}
