package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Ticket {
    String ticketId;
    String gateId;
    String slotId;
    String vehicleId;
    int durationMinutes;
    Payment payment;
    TicketStatus ticketStatus;
    LocalDateTime issuedAt;

    public void setTicketStatus(TicketStatus ticketStatus){
        this.ticketStatus = ticketStatus;
    }

    public void setPayment(Payment payment){
        this.payment = payment;
    }

    public void setIssuedAt(LocalDateTime issuedAt){
        this.issuedAt = issuedAt;
    }

}
