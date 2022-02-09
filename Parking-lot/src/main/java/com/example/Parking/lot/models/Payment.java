package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.PaymentMode;
import com.example.Parking.lot.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Payment {
    String paymentId;
    PaymentMode paymentMode;
    PaymentStatus paymentStatus;
    int amount;
    Ticket ticket;
}
