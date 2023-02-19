package com.twentythree.gameberry;

import com.twentythree.gameberry.beans.Restaurant;
import com.twentythree.gameberry.beans.User;

import java.util.List;

public interface IRestaurantOperations {
    List<Restaurant> getRestaurantRecommendations(User user, Restaurant[] availableRestaurants);
}
