package com.designpatterns.decorator;

public class TomatoSauce extends ToppingDecorator{

    public TomatoSauce(Pizza newPizza) {
        super(newPizza);
        System.out.println("Adding sauce");
    }

    @Override
    public String getToppings() {
        return pizza.getToppings() + " Sauce";
    }

    @Override
    public Double getCost() {
        return pizza.getCost() + 20.0;
    }
}
