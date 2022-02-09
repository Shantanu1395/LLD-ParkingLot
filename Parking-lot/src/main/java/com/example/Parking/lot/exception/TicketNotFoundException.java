package com.example.Parking.lot.exception;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String ex){
        super(ex);
    }
}
