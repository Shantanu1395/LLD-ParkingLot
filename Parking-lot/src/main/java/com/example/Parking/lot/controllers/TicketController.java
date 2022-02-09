package com.example.Parking.lot.controllers;

import com.example.Parking.lot.enums.PaymentMode;
import com.example.Parking.lot.enums.SlotType;

import com.example.Parking.lot.services.TicketService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class TicketController {

    TicketService ticketService;
    public String generateTicket(String vehicleNumber, SlotType vehicleType, String gateId, String parkingId){
        return ticketService.generateTicket(vehicleNumber, vehicleType, gateId, parkingId).getTicketId();
    }

    public void checkoutVehicle(String vehicleNo1) {
        ticketService.checkoutVehicle(vehicleNo1);
    }

    public int payFees(String vehicleId, PaymentMode paymentMode) {
        return ticketService.payFees(vehicleId, paymentMode);
    }

    public void setTicketIssuedAt(String ticket1, LocalDateTime minusHours) {
        ticketService.setTicketIssuedAt(ticket1, minusHours);
    }
}
