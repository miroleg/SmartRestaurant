package org.java.smartrestaurant.util.entity;

import org.java.smartrestaurant.dto.RestaurantDto;
import org.java.smartrestaurant.model.Dish;
import org.java.smartrestaurant.model.MenuItem;
import org.java.smartrestaurant.model.Restaurant;
import org.java.smartrestaurant.model.Vote;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static RestaurantDto createDtoFrom(Restaurant restaurant) {
        return new RestaurantDto(restaurant.getId(), restaurant.getName(),
                restaurant.getDescription(), restaurant.getContact(), restaurant.getSite(),
                restaurant.getEmail(), restaurant.getPhones());
    }

    public static List<RestaurantDto> createDtoListFromRestaurantList(List<Restaurant> restaurantList) {
        return restaurantList.stream().map(el -> new RestaurantDto(el.getId(), el.getName(),
                el.getDescription(), el.getContact(), el.getSite(),
                el.getEmail(), el.getPhones())).collect(Collectors.toList());
    }

    public static Restaurant createRestaurantFromDto(RestaurantDto restaurant) {
        return new Restaurant(restaurant.getId(), restaurant.getName(),
                restaurant.getDescription(), restaurant.getContact(), restaurant.getSite(),
                restaurant.getEmail(), restaurant.getPhones(), Collections.emptySet(), Collections.emptySet(), Collections.emptySet() );
    }

    public static Restaurant updateRestaurantFromDto(Restaurant restaurant, RestaurantDto restaurantDto) {
        restaurant.setName(restaurantDto.getName());
        restaurant.setDescription(restaurantDto.getDescription());
        restaurant.setContact(restaurantDto.getContact());
        restaurant.setSite(restaurantDto.getSite());
        restaurant.setEmail(restaurantDto.getEmail().toLowerCase());
        restaurant.setPhones(restaurantDto.getPhones());
        return restaurant;
    }

    public static Restaurant createNewFromAnother(Restaurant restaurant) {
        return new Restaurant(
                restaurant.getId(), restaurant.getName(), restaurant.getDescription(),
                restaurant.getContact(), restaurant.getSite(), restaurant.getEmail(),
                restaurant.getPhones(), new HashSet<Dish>(restaurant.getDishes()), new HashSet<MenuItem>(restaurant.getMenuItems()), new HashSet<Vote>(restaurant.getVotes()) ) ;
    }

}
