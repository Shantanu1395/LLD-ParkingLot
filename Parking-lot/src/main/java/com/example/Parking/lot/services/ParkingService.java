package com.example.Parking.lot.services;

import com.example.Parking.lot.enums.GateType;
import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import com.example.Parking.lot.exception.*;
import com.example.Parking.lot.models.*;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ParkingService {

    private Map<String, Parking> parkingMap;
    private Map<String, Floor> floorMap;
    private Map<String, Gate> gateMap;
    private Map<String, Slot> slotMap;
    private Map<String, Vehicle> vehicleMap;


    public ParkingService() {
        this.parkingMap = new HashMap<>();
        this.floorMap = new HashMap<>();
        this.gateMap = new HashMap<>();
        this.slotMap = new HashMap<>();
        this.vehicleMap = new HashMap<>();
    }

    public Parking createParking(@NonNull final String parkingName) {

        String parkingId = UUID.randomUUID().toString();
        Parking parking = new Parking(parkingId, parkingName);
        parkingMap.put(parkingId, parking);
        return parking;

    }

    public Gate createGate(@NonNull final String gateName, @NonNull final GateType gateType, @NonNull final String parkingId) {

        if(!parkingMap.containsKey(parkingId)) throw new GateNotFoundException("Error, parking not found in datastore");

        Parking  parking = parkingMap.get(parkingId);
        String gateId = UUID.randomUUID().toString();
        Gate gate = new Gate(gateId, gateName, gateType, parking);
        parking.addGatesToParking(gate);
        gateMap.put(gateId, gate);
        return gate;
    }

    public Slot createSlot(@NonNull final SlotType slotType, @NonNull final String floorId) {

        if(!floorMap.containsKey(floorId)) throw new FloorNotFoundException("Error, floor not found in data store");

        Floor floor = floorMap.get(floorId);
        String slotId = UUID.randomUUID().toString();
        Slot slot = new Slot(slotId, slotType, SlotStatus.AVAILABLE, floor);
        floor.addSlotsToFloor(slot);
        slotMap.put(slotId, slot);
        return slot;
    }

    public Floor createFloor(@NonNull final String floorName, @NonNull final String parkingId) {

        if(!parkingMap.containsKey(parkingId)) throw new ParkingNotFoundException("Error, parking not found in datastore");

        Parking parking = parkingMap.get(parkingId);
        String floorId = UUID.randomUUID().toString();
        Floor floor = new Floor(floorId, floorName, parking);
        floorMap.put(floorId, floor);
        parking.addFloorsToParking(floor);
        return floor;
    }

    public List<Parking> getParkings(){
        return parkingMap.values().stream().toList();
    }

    public Slot getSlot(@NonNull final SlotType vehicleType, @NonNull final String parkingId) {

        if(!parkingMap.containsKey(parkingId)) throw new ParkingNotFoundException("Error, parking not present in datastore");

        Parking parking = parkingMap.get(parkingId);
        List<Floor> floors = parking.getFloors();
        for (Floor floor:floors){
            List<Slot> slots = floor.getSlots().stream().filter(slot -> slot.getSlotType().equals(vehicleType) && slot.getSlotStatus().equals(SlotStatus.AVAILABLE)).collect(Collectors.toList());
            if (slots.size() > 0){
                return slots.get(0);
            }
        }
        return null;
    }

    public void markSlotOccupied(@NonNull final Slot slot, @NonNull final String vehicleNumber) {

        if(!vehicleMap.containsKey(vehicleNumber)) throw new VehicleNotFoundException("Error, vehicle not found in datastore");

        Vehicle vehicle = vehicleMap.get(vehicleNumber);

        slot.assignVehicleToSlot(vehicle);
        slot.setSlotStatus(SlotStatus.BOOKED);
    }

    public void registerVehicle(@NonNull final String vehicleNumber, @NonNull final SlotType vehicleType){

        Vehicle vehicle = new Vehicle(vehicleNumber, vehicleType);
        vehicleMap.put(vehicleNumber, vehicle);
    }

}
