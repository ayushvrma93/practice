package com.twentythree.gameberry.beans;

import com.twentythree.gameberry.beans.enums.Cuisine;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuisineTracking {

    private Cuisine type;
    private Integer noOfOrders;
}
