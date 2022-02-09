package com.designpatterns.decorator;

public class PizzaDecorator {

    public static void main(String[] args) {

        Pizza basicPizza = new TomatoSauce(new Mozzarella(new PlainPizza()));
        System.out.println(basicPizza.getToppings());
        System.out.println(basicPizza.getCost());
    }
}
