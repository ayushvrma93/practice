package com.twentythree.gameberry;

import com.twentythree.gameberry.beans.Restaurant;
import com.twentythree.gameberry.beans.User;
import com.twentythree.gameberry.beans.enums.Cuisine;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.twentythree.gameberry.Constants.*;

public class RestaurantOperations implements IRestaurantOperations{

    private static final IRestaurantOperations INSTANCE = new RestaurantOperations();

    public static IRestaurantOperations getInstance(){
        return INSTANCE;
    }

    @Data
    private class UserCuisinePreferences{
        private Set<Cuisine> primaryCuisine;
        private Set<Cuisine> secondaryCuisines;
    }

    @Data
    private class UserCostPreferences{

        private Set<Integer> primaryCostBracket;
        private Set<Integer> secondaryCostBracket;
    }

    public List<Restaurant> getRestaurantRecommendations(User user, Restaurant[] availableRestaurants) {

        Arrays.sort(availableRestaurants, Comparator.comparing(Restaurant::getRating, Comparator.reverseOrder()));
        List<Restaurant> availableRestaurantList = new LinkedList<>(Arrays.asList(availableRestaurants));
        List<Restaurant> recommendedRestaurants = new ArrayList<>();

        UserCuisinePreferences cuisinePreferences = new UserCuisinePreferences();
        cuisinePreferences.setPrimaryCuisine(getOrderedPreferences(user, 0, 0));
        cuisinePreferences.setSecondaryCuisines(getOrderedPreferences(user, 1, 2));

        UserCostPreferences costPreferences = new UserCostPreferences();
        costPreferences.setPrimaryCostBracket(getOrderedCostPreferences(user, 0, 0));
        costPreferences.setSecondaryCostBracket(getOrderedCostPreferences(user, 1, 2));

        recommendedRestaurants = getFeaturedRestaurants(recommendedRestaurants, user, availableRestaurantList, cuisinePreferences, costPreferences);
        recommendedRestaurants = addFilteredRestaurants(availableRestaurantList, recommendedRestaurants, cuisinePreferences.getPrimaryCuisine(), costPreferences.getPrimaryCostBracket()
                , 4.0f, 5.0f);
        recommendedRestaurants = addFilteredRestaurants(availableRestaurantList, recommendedRestaurants, cuisinePreferences.getPrimaryCuisine(), costPreferences.getSecondaryCostBracket()
                , 4.5f, 5.0f);
        recommendedRestaurants = addFilteredRestaurants(availableRestaurantList, recommendedRestaurants, cuisinePreferences.getSecondaryCuisines(), costPreferences.getPrimaryCostBracket()
                , 4.5f, 5.0f);
        recommendedRestaurants = getNewRestaurants(availableRestaurantList, recommendedRestaurants, NEW_RESTAURANT_COUNT);
        recommendedRestaurants = addFilteredRestaurants(availableRestaurantList, recommendedRestaurants, cuisinePreferences.getPrimaryCuisine(), costPreferences.getPrimaryCostBracket()
                , 0.0f, 3.99f);
        recommendedRestaurants = addFilteredRestaurants(availableRestaurantList, recommendedRestaurants, cuisinePreferences.getPrimaryCuisine(), costPreferences.getSecondaryCostBracket()
                , 0.0f, 4.49f);
        recommendedRestaurants = addFilteredRestaurants(availableRestaurantList, recommendedRestaurants, cuisinePreferences.getSecondaryCuisines(), costPreferences.getPrimaryCostBracket()
                , 0.0f, 4.49f);
        recommendedRestaurants.addAll(availableRestaurantList);

        if(recommendedRestaurants.size() > NO_OF_RECOMMENDATIONS) recommendedRestaurants.subList(0,NO_OF_RECOMMENDATIONS).clear();
        return recommendedRestaurants;
    }

    private List<Restaurant> getFeaturedRestaurants(List<Restaurant> recommendedRestaurants, User user, List<Restaurant> restaurantList, UserCuisinePreferences cuisinePreferences
            , UserCostPreferences costPreferences){

        List<Restaurant> primaryCuisineAndCost = new ArrayList<>();
        List<Restaurant> secondaryCuisineOrCost = new ArrayList<>();

        Iterator<Restaurant> itr = restaurantList.listIterator();

        while(itr.hasNext()){

            Restaurant restaurant = itr.next();

            if(restaurant.isRecommended() && cuisinePreferences.getPrimaryCuisine().contains(restaurant.getCuisine()) && costPreferences.getPrimaryCostBracket()
                    .contains(restaurant.getCostBracket())){
                primaryCuisineAndCost.add(restaurant);
                itr.remove();

            }

            if((cuisinePreferences.getPrimaryCuisine().contains(restaurant.getCuisine()) && costPreferences.getSecondaryCostBracket()
                    .contains(restaurant.getCostBracket())) || (cuisinePreferences.getSecondaryCuisines().contains(restaurant.getCuisine())
                    && costPreferences.getPrimaryCostBracket()
                    .contains(restaurant.getCostBracket())) ){

                secondaryCuisineOrCost.add(restaurant);
            }
        }

        if(!primaryCuisineAndCost.isEmpty()){
            recommendedRestaurants.addAll(primaryCuisineAndCost);
        }

        else {
            recommendedRestaurants.addAll(secondaryCuisineOrCost);
            restaurantList.removeAll(secondaryCuisineOrCost);
        }
        return recommendedRestaurants;
    }


    private List<Restaurant> addFilteredRestaurants(List<Restaurant> restaurantList, List<Restaurant> recommendedRestaurants, Set<Cuisine> cuisines
            , Set<Integer> costTrackings, Float ratingLowerBound, Float ratingUpperBound){

        Iterator<Restaurant> itr = restaurantList.listIterator();

        while (itr.hasNext()){

            Restaurant restaurant = itr.next();

            if(restaurant.getRating() < ratingLowerBound) break;

            if(cuisines.contains(restaurant.getCuisine()) && costTrackings.contains(restaurant.getCostBracket())
                    && restaurant.getRating() >= ratingLowerBound && restaurant.getRating() <=ratingUpperBound ){
                recommendedRestaurants.add(restaurant);
                itr.remove();
            }
        }
        return recommendedRestaurants;
    }

    private List<Restaurant> getNewRestaurants(List<Restaurant> restaurantList, List<Restaurant> recommendedRestaurants, int count){

        Iterator<Restaurant> itr = restaurantList.listIterator();

        while(itr.hasNext()){

            Restaurant restaurant = itr.next();

            if(count > 0){
                if(ChronoUnit.HOURS.between(restaurant.getOnboardedTime(), LocalDateTime.now()) < NEW_RESTAURANT_CUTOFF_HOURS){
                    recommendedRestaurants.add(restaurant);
                    itr.remove();
                    count--;
                }
            }
        }
        return recommendedRestaurants;
    }

    private Set<Cuisine> getOrderedPreferences(User user, int startIndex, int endIndex){
        Set<Cuisine> orderedCuisines = new HashSet<>();

        for(int i=startIndex; i<=endIndex; i++){
            orderedCuisines.add(user.getCuisines()[i].getType());
        }
        return orderedCuisines;
    }

    private Set<Integer> getOrderedCostPreferences(User user, int startIndex, int endIndex){
        Set<Integer> orderedCostPreferences = new HashSet<>();
        for(int i=startIndex; i<=endIndex; i++){
            orderedCostPreferences.add(user.getCostBracket()[i].getType());
        }
        return orderedCostPreferences;
    }
}
