package org.java.smartrestaurant.service.dish;

import org.java.smartrestaurant.model.Dish;
import org.java.smartrestaurant.service.BaseService;

import java.util.List;

public interface DishService extends BaseService<Dish> {
    List<Dish> readByNameLike(String name);
//    List<Dish> readPaginated(int page, int size);
    void deleteAllByRestaurantId(int restaurantId);
    public List<Dish> getDishByRestaurantId(int id);
}
