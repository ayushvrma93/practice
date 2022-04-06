package com.flipkart.machinecoding.entity.enums;

public enum City {

    BANGALORE("BANGALORE"),
    MUMBAI("MUMBAI");

    private String value;

    City(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}