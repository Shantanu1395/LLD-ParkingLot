package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Ticket {

    private String ticketId;
    private String gateId;
    private String slotId;
    private String vehicleId;
    private int durationMinutes;
    private Payment payment;
    private TicketStatus ticketStatus;
    private LocalDateTime issuedAt;

    public void setTicketStatus(@NonNull final TicketStatus ticketStatus){
        this.ticketStatus = ticketStatus;
    }

    public void setPayment(@NonNull final Payment payment){
        this.payment = payment;
    }

    public void setIssuedAt(@NonNull final LocalDateTime issuedAt){
        this.issuedAt = issuedAt;
    }

}
