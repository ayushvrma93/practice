package com.twentytwo.flipkart.machinecoding.entity.enums;

public enum BookingStatus {

    BOOKED("BOOKED"),
    CANCELLED("CANCELLED");

    private String value;

    BookingStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
