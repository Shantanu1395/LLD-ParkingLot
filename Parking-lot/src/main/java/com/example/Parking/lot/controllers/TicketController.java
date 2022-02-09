package com.example.Parking.lot.controllers;

import com.example.Parking.lot.enums.PaymentMode;
import com.example.Parking.lot.enums.SlotType;

import com.example.Parking.lot.services.TicketService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@AllArgsConstructor
public class TicketController {

    TicketService ticketService;
    public String generateTicket(@NonNull final String vehicleNumber, @NonNull final SlotType vehicleType, @NonNull final String gateId, @NonNull final String parkingId){
        return ticketService.generateTicket(vehicleNumber, vehicleType, gateId, parkingId).getTicketId();
    }

    public void checkoutVehicle(@NonNull final String vehicleNo1) {
        ticketService.checkoutVehicle(vehicleNo1);
    }

    public int payFees(@NonNull final String vehicleId,@NonNull final PaymentMode paymentMode) {
        return ticketService.payFees(vehicleId, paymentMode);
    }

    public void setTicketIssuedAt(@NonNull final String ticket1,@NonNull final LocalDateTime minusHours) {
        ticketService.setTicketIssuedAt(ticket1, minusHours);
    }
}
