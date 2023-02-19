package com.twentythree.gameberry;

import com.twentythree.gameberry.beans.CostTracking;
import com.twentythree.gameberry.beans.CuisineTracking;
import com.twentythree.gameberry.beans.Restaurant;
import com.twentythree.gameberry.beans.User;
import com.twentythree.gameberry.beans.enums.Cuisine;

import java.time.LocalDateTime;
import java.util.List;

public class Driver {

    public static void main(String[] args){

        IRestaurantOperations restaurantOperations = RestaurantOperations.getInstance();

        Restaurant restaurant1 = new Restaurant("Restaurant1", Cuisine.NORTH_INDIAN, 4, 4.1f, true
                , LocalDateTime.of(2022, 12, 28, 0, 20));

        Restaurant restaurant2 = new Restaurant("Restaurant2", Cuisine.SOUTH_INDIAN, 4, 3.1f, true
                , LocalDateTime.of(2022, 12, 28, 0, 20));

        Restaurant restaurant3 = new Restaurant("Restaurant3", Cuisine.CHINESE, 3, 3.1f, true
                , LocalDateTime.of(2022, 12, 28, 0, 20));

        Restaurant restaurant4 = new Restaurant("Restaurant4", Cuisine.NORTH_INDIAN, 4, 2.8f, false
                , LocalDateTime.of(2022, 12, 28, 0, 20));

        //onboarding date should be greater than currDate - 48 hours
        Restaurant restaurant5 = new Restaurant("Restaurant5", Cuisine.NORTH_INDIAN, 1, 2.3f, true
                , LocalDateTime.of(2023, 02, 18, 0, 20));

        Restaurant[] restaurants = new Restaurant[5];
        restaurants[0] = restaurant1;
        restaurants[1] = restaurant2;
        restaurants[2] = restaurant3;
        restaurants[3] = restaurant4;
        restaurants[4] = restaurant5;

        CuisineTracking[] cuisineTrackings = new CuisineTracking[3];

        CuisineTracking cuisineTracking1 = new CuisineTracking(Cuisine.SOUTH_INDIAN, 3);
        CuisineTracking cuisineTracking2 = new CuisineTracking(Cuisine.NORTH_INDIAN, 5);
        CuisineTracking cuisineTracking3 = new CuisineTracking(Cuisine.CHINESE, 1);
        cuisineTrackings[0] = cuisineTracking1;
        cuisineTrackings[1] = cuisineTracking2;
        cuisineTrackings[2] = cuisineTracking3;

        CostTracking[] costTrackings = new CostTracking[3];
        CostTracking costTracking1 = new CostTracking(4, 5);
        CostTracking costTracking2 = new CostTracking(2, 1);
        CostTracking costTracking3 = new CostTracking(3, 2);
        costTrackings[0] = costTracking1;
        costTrackings[1] = costTracking2;
        costTrackings[2] = costTracking3;

        User user1 = new User(cuisineTrackings, costTrackings);

        List<Restaurant> recommendedRestaurants = restaurantOperations.getRestaurantRecommendations(user1, restaurants);

        System.out.println(recommendedRestaurants);
    }
}
