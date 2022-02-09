package com.designpatterns.decorator;

public class ToppingDecorator implements Pizza{

    protected Pizza pizza;

    public ToppingDecorator(Pizza newPizza){
        pizza = newPizza;
    }

    @Override
    public String getToppings() {
        return pizza.getToppings();
    }

    @Override
    public Double getCost() {
        return pizza.getCost();
    }
}
