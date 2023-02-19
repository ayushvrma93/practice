package com.twentytwo.designpatterns.decorator;

public class PlainPizza implements Pizza {

    @Override
    public String getToppings() {
        return "Plain Pizza";
    }

    @Override
    public Double getCost() {
        return 125.0;
    }
}
