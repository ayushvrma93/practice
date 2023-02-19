package com.twentythree.gameberry.beans;

import com.twentythree.gameberry.beans.enums.Cuisine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Restaurant {

    private String restaurantId;
    private Cuisine cuisine;
    private int costBracket;
    private float rating;
    private boolean isRecommended;
    private LocalDateTime onboardedTime;
}
