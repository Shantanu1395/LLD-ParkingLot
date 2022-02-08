package com.example.Parking.lot;

import com.example.Parking.lot.controllers.ParkingController;
import com.example.Parking.lot.enums.GateType;
import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import com.example.Parking.lot.models.Parking;
import com.example.Parking.lot.services.ParkingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    ParkingController parkingController;

    public void setupControllers(){
        ParkingService parkingService = new ParkingService();
        parkingController = new ParkingController(parkingService);
    }

    @Test
    public void testInfra(){
        setupControllers();

        String parkingId = parkingController.createParking("Deltin Parking");

        String gate1 = parkingController.createGate("Gate1", GateType.IN, parkingId);
        String gate2 = parkingController.createGate("Gate2", GateType.IN, parkingId);
        String gate3 = parkingController.createGate("Gate3", GateType.IN, parkingId);
        String gate4 = parkingController.createGate("Gate3", GateType.OUT, parkingId);
        String gate5 = parkingController.createGate("Gate3", GateType.OUT, parkingId);

        String floor1 = parkingController.createFloor("Floor1", parkingId);
        String floor2 = parkingController.createFloor("Floor2", parkingId);
        String floor3 = parkingController.createFloor("Floor3", parkingId);

        List<List<String>> floorSlots = new ArrayList<>();
        List<String> floor1Slots = new ArrayList<>();
        List<String> floor2Slots = new ArrayList<>();
        List<String> floor3Slots = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            floor1Slots.add(parkingController.createSlot(SlotType.ELECTRIC_CAR, SlotStatus.AVAILABLE, floor1));
            floor1Slots.add(parkingController.createSlot(SlotType.TRUCK, SlotStatus.AVAILABLE, floor1));
            floor1Slots.add(parkingController.createSlot(SlotType.VAN, SlotStatus.AVAILABLE, floor1));
        }
        for(int i = 0; i < 10; i++){
            floor2Slots.add(parkingController.createSlot(SlotType.CAR, SlotStatus.AVAILABLE, floor2));
        }
        for(int i = 0; i < 20; i++){
            floor3Slots.add(parkingController.createSlot(SlotType.MOTOR_BIKE, SlotStatus.AVAILABLE, floor3));
        }
        floorSlots.add(floor1Slots);
        floorSlots.add(floor2Slots);
        floorSlots.add(floor3Slots);

        Parking parking = parkingController.getParkings().get(0);
        Assertions.assertEquals(parking.getFloors().size(), 3);
        Assertions.assertEquals(parking.getGates().size(), 5);

        //Create car
        //Check if car can be parked
        //Generate ticket
        //Park Car
        //Pay Fees and checkout car

    }

}
