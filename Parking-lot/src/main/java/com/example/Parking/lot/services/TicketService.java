package com.example.Parking.lot.services;

import com.example.Parking.lot.constants.Constants;
import com.example.Parking.lot.enums.*;
import com.example.Parking.lot.models.Payment;
import com.example.Parking.lot.models.Slot;
import com.example.Parking.lot.models.Ticket;
import com.example.Parking.lot.models.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TicketService {

    Map<String, Ticket> ticketMap;
    ParkingService parkingService;
    Map<String, Vehicle> vehicleMap;
    Map<String, Payment> paymentMap;

    public TicketService(ParkingService parkingService) {
        this.parkingService = parkingService;
        this.ticketMap = new HashMap<>();
        this.vehicleMap = new HashMap<>();
        this.paymentMap = new HashMap<>();
    }

    public Ticket generateTicket(String vehicleNumber, SlotType vehicleType, String gateId, String parkingId){
        Slot slot = parkingService.getSlot(vehicleType, parkingId);
        if (slot == null){
            throw new RuntimeException("Can not assign slot for this vehicle type");
        }
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(ticketId, gateId, slot.getId(), vehicleNumber, 0, null, TicketStatus.ONGOING, LocalDateTime.now());
        Vehicle vehicle = new Vehicle(vehicleNumber, vehicleType);
        vehicle.addTrip(ticket);
        vehicle.assignSlot(slot);
        vehicleMap.put(vehicleNumber, vehicle);
        parkingService.markSlotOccupied(slot, vehicleNumber);
        ticketMap.put(ticketId, ticket);
        return ticket;
    }

    //Implement payed at using strategy design pattern
    public int payFees(String vehicleId, PaymentMode paymentMode){
        if(!vehicleMap.containsKey(vehicleId))
            throw new RuntimeException("Vehicle doesn't exist");
        Vehicle vehicle = vehicleMap.get(vehicleId);
        List<Ticket> ticketList = vehicle.getTrips();
        Ticket ticket = ticketList.get(ticketList.size() - 1);
        ticket.setTicketStatus(TicketStatus.FEES_PAID);

        String paymentId = UUID.randomUUID().toString();
        long hours = ChronoUnit.HOURS.between(ticket.getIssuedAt(), LocalDateTime.now());
        int cost;
        int aboveTwoHours = (int)hours - 2;

        //More than 2 hours
        if(aboveTwoHours > 0){
            cost = (2 * Constants.FIRST_TWO_HOUR_RATE) + (aboveTwoHours * Constants.ABOVE_TWO_HOUR_RATE);
        }else{
            cost = Constants.FIRST_TWO_HOUR_RATE * (int)hours;
        }

        Payment payment = new Payment(paymentId, paymentMode, PaymentStatus.COMPLETED, cost, ticket);
        ticket.setPayment(payment);

        paymentMap.put(paymentId, payment);
        return cost;
    }

    public void checkoutVehicle(String vehicleId) {
        if(!vehicleMap.containsKey(vehicleId))
            throw new RuntimeException("Vehicle doesn't exist");
        Vehicle vehicle = vehicleMap.get(vehicleId);
        Ticket currentTicket = vehicle.getTrips().get(0);
        if(currentTicket.getTicketStatus().equals(TicketStatus.FEES_PAID)){
            vehicle.getSlot().setSlotStatus(SlotStatus.AVAILABLE);
            currentTicket.setTicketStatus(TicketStatus.CHECKOUT_DONE);
        }else{
            throw new RuntimeException("Can't checkout vehicle, pay fee first");
        }
    }

    public void setTicketIssuedAt(String ticketId, LocalDateTime time) {
        if(!ticketMap.containsKey(ticketId)) throw new RuntimeException("Error, ticket not found");
        Ticket ticket = ticketMap.get(ticketId);
        ticket.setIssuedAt(time);
    }
}
