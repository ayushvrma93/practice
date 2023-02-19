package com.twentytwo.meesho.entity.enums;

public enum PaymentMode {

    COD("COD"),
    PREPAID("PREPAID");

    private String value;

    PaymentMode(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
