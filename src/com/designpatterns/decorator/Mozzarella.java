package com.designpatterns.decorator;

public class Mozzarella extends ToppingDecorator{

    public Mozzarella(Pizza newPizza) {
        super(newPizza);
        System.out.println("Adding mozzarella");
    }

    @Override
    public String getToppings() {
        return pizza.getToppings() + " Moz";
    }

    @Override
    public Double getCost() {
        return pizza.getCost() + 70.0;
    }
}
