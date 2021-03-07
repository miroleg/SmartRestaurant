package org.java.smartrestaurant.service.restaurant;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Restaurant;
import org.java.smartrestaurant.service.BaseService;

public interface RestaurantService extends BaseService<Restaurant> {
    Restaurant readByName(String name) throws NotFoundException;
}
