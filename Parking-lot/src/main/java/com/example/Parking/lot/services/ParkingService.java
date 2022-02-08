package com.example.Parking.lot.services;

import com.example.Parking.lot.enums.GateType;
import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import com.example.Parking.lot.models.Floor;
import com.example.Parking.lot.models.Gate;
import com.example.Parking.lot.models.Parking;
import com.example.Parking.lot.models.Slot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ParkingService {

    Map<String, Parking> parkingMap = new HashMap<>();
    Map<String, Floor> floorMap = new HashMap<>();
    Map<String, Gate> gateMap = new HashMap<>();
    Map<String, Slot> slotMap = new HashMap<>();

    public Parking createParking(String name) {
        String parkingId = UUID.randomUUID().toString();
        Parking parking = new Parking(parkingId, name);
        parkingMap.put(parkingId, parking);
        return parking;
    }

    public Gate createGate(String gateName, GateType gateType, String parkingId) {

        Parking parking = null;
        if(parkingMap.containsKey(parkingId)){
            parking = parkingMap.get(parkingId);
        }else{
            //throw Exception
        }

        String gateId = UUID.randomUUID().toString();
        Gate gate = new Gate(gateId, gateName, gateType, parking);
        parking.addGatesToParking(gate);
        gateMap.put(gateId, gate);
        return gate;
    }

    public Slot createSlot(SlotType slotType, SlotStatus slotStatus, String floorId) {

        Floor floor = null;
        if(floorMap.containsKey(floorId)){
            floor = floorMap.get(floorId);
        }else{
            //Exception
        }

        String slotId = UUID.randomUUID().toString();
        Slot slot = new Slot(slotId, slotType, SlotStatus.AVAILABLE, floor);
        floor.addSlotsToFloor(slot);
        slotMap.put(slotId, slot);
        return slot;
    }

    public Floor createFloor(String floorName, String parkingId) {

        Parking parking = null;
        if(parkingMap.containsKey(parkingId)){
            parking = parkingMap.get(parkingId);
        }else{
            //throw Exception
        }

        String floorId = UUID.randomUUID().toString();
        Floor floor = new Floor(floorId, floorName, parking);
        floorMap.put(floorId, floor);
        parking.addFloorsToParking(floor);
        return floor;
    }

    public void addGateToParking(String parkingId, String gateId) {
        if(gateMap.containsKey(gateId)){
            if(parkingMap.containsKey(parkingId)){
                parkingMap.get(parkingId).addGatesToParking(gateMap.get(gateId));
            }else{
                //ThrowException
            }
        }else{
            //Exception
        }
    }

    public void addFloorToParking(String parkingId, String floorId) {
        if(floorMap.containsKey(floorId)){
            if(parkingMap.containsKey(parkingId)){
                parkingMap.get(parkingId).addFloorsToParking(floorMap.get(floorId));
            }else{
                //ThrowException
            }
        }else{
            //Exception
        }
    }

    public void addSlotToFloor(String slotId, String floorId) {
        if(floorMap.containsKey(floorId)){
            if(slotMap.containsKey(slotId)){
                floorMap.get(floorId).addSlotsToFloor(slotMap.get(slotId));
            }else{
                //ThrowException
            }
        }else{
            //Exception
        }
    }

    public List<Parking> getParkings(){
        return parkingMap.values().stream().toList();
    }

}
