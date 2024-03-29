package com.twentythree.gameberry.beans;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;

@Data
public class User {

    private CuisineTracking[] cuisines;
    private CostTracking[] costBracket;

    public User(CuisineTracking[] cuisineTrackings, CostTracking[] costBracket){

        this.cuisines = cuisineTrackings;
        this.costBracket = costBracket;
        Arrays.sort(cuisines, Comparator.comparing(CuisineTracking::getNoOfOrders, Comparator.reverseOrder()));
        Arrays.sort(costBracket, Comparator.comparing(CostTracking::getNoOfOrders, Comparator.reverseOrder()));
    }

}
