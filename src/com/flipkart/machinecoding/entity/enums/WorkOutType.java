package com.flipkart.machinecoding.entity.enums;

public enum WorkOutType {

    WEIGHTS("WEIGHTS"),
    CARDIO("CARDIO"),
    YOGA("YOGA"),
    SWIMMING("SWIMMING");

    private String value;

    WorkOutType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
