package com.example.Parking.lot.models;

import com.example.Parking.lot.enums.PaymentMode;
import com.example.Parking.lot.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Payment {
    private String paymentId;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private int amount;
    private Ticket ticket;
}
