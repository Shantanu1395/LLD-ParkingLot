package com.example.Parking.lot.exception;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String ex){
        super(ex);
    }
}
