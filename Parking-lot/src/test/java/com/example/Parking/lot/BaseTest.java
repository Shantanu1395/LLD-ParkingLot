package com.example.Parking.lot;

import com.example.Parking.lot.controllers.ParkingController;
import com.example.Parking.lot.controllers.TicketController;
import com.example.Parking.lot.enums.GateType;
import com.example.Parking.lot.enums.PaymentMode;
import com.example.Parking.lot.enums.SlotStatus;
import com.example.Parking.lot.enums.SlotType;
import com.example.Parking.lot.models.Parking;
import com.example.Parking.lot.services.ParkingService;
import com.example.Parking.lot.services.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    ParkingController parkingController;
    TicketController ticketController;

    public void setupControllers(){
        ParkingService parkingService = new ParkingService();
        TicketService ticketService = new TicketService(parkingService);
        parkingController = new ParkingController(parkingService);
        ticketController = new TicketController(ticketService);
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
            floor1Slots.add(parkingController.createSlot(SlotType.ELECTRIC_CAR, floor1));
            floor1Slots.add(parkingController.createSlot(SlotType.TRUCK, floor1));
            floor1Slots.add(parkingController.createSlot(SlotType.VAN, floor1));
        }
        for(int i = 0; i < 10; i++){
            floor2Slots.add(parkingController.createSlot(SlotType.CAR, floor2));
        }
        for(int i = 0; i < 20; i++){
            floor3Slots.add(parkingController.createSlot(SlotType.MOTOR_BIKE, floor3));
        }
        floorSlots.add(floor1Slots);
        floorSlots.add(floor2Slots);
        floorSlots.add(floor3Slots);

        Parking parking = parkingController.getParkingList().get(0);
        Assertions.assertEquals(parking.getFloors().size(), 3);
        Assertions.assertEquals(parking.getGates().size(), 5);

        //1. Generate vehicle
        //2. Generate ticket
        //3. Pay Fees and checkout car

        //1. Generate vehicle
        String vehicleNo1 = parkingController.registerVehicle("101231", SlotType.ELECTRIC_CAR);
        String vehicleNo2 = parkingController.registerVehicle("101232", SlotType.ELECTRIC_CAR);
        String vehicleNo3 = parkingController.registerVehicle("101233", SlotType.ELECTRIC_CAR);
        String vehicleNo4 = parkingController.registerVehicle("101234", SlotType.ELECTRIC_CAR);
        String vehicleNo5 = parkingController.registerVehicle("101235", SlotType.ELECTRIC_CAR);
        String vehicleNo6 = parkingController.registerVehicle("101236", SlotType.ELECTRIC_CAR);

        //2. Generate ticket
        String ticket1 = ticketController.generateTicket(vehicleNo1, SlotType.ELECTRIC_CAR, gate1, parkingId);
        ticketController.setTicketIssuedAt(ticket1, LocalDateTime.now().minusHours(5));
        String ticket2 = ticketController.generateTicket(vehicleNo2, SlotType.ELECTRIC_CAR, gate1, parkingId);
        String ticket3 = ticketController.generateTicket(vehicleNo3, SlotType.ELECTRIC_CAR, gate1, parkingId);
        String ticket4 = ticketController.generateTicket(vehicleNo4, SlotType.ELECTRIC_CAR, gate1, parkingId);
        String ticket5 = ticketController.generateTicket(vehicleNo5, SlotType.ELECTRIC_CAR, gate1, parkingId);

        //Assertions
        //Will throw Exception - No slots available for Electric Car
        try {
            String ticket6 = ticketController.generateTicket(vehicleNo6, SlotType.ELECTRIC_CAR, gate1, parkingId);
        }catch (RuntimeException runtimeException){
            Assertions.assertEquals(runtimeException.getMessage(), "Can not assign slot for this vehicle type");
        }

        //Pay fees before checkout
        try {
            ticketController.checkoutVehicle(vehicleNo1);
        }catch (RuntimeException runtimeException){
            Assertions.assertEquals(runtimeException.getMessage(), "Can't checkout vehicle, pay fee first");
        }

        //Fees paid now vehicle can be checked checkout
        int fees = ticketController.payFees(vehicleNo1, PaymentMode.CREDIT_CARD);
        ticketController.checkoutVehicle(vehicleNo1);

        //Assert fees paid
        Assertions.assertEquals(fees, 350);

        //Will be able to book slot now
        String ticket6 = ticketController.generateTicket(vehicleNo6, SlotType.ELECTRIC_CAR, gate1, parkingId);

    }

}
