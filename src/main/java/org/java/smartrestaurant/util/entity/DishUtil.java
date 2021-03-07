package org.java.smartrestaurant.util.entity;

import org.java.smartrestaurant.dto.DishDto;
import org.java.smartrestaurant.model.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class DishUtil {
    public static DishDto createDtoFrom(Dish dish) {
        return new DishDto(dish.getId(), dish.getName(),
                dish.getDescription());
    }

    public static List<DishDto> createDtoListFromDishList(List<Dish> dishList) {
        return dishList.stream().map(el -> new DishDto(el.getId(), el.getName(),
                el.getDescription())).collect(Collectors.toList());
    }

    public static Dish createDishFromDto(DishDto dishDto) {
        return new Dish(dishDto.getId(), dishDto.getName(),
                dishDto.getDescription(), null);
    }

    public static Dish updateDishFromDto(Dish dish, DishDto dishDto) {
        dish.setName(dishDto.getName());
        dish.setDescription(dishDto.getDescription());
        return dish;
    }

    public static Dish createNewFromAnother(Dish dish) {
        return new Dish(
                dish.getId(), dish.getName(), dish.getDescription(),
                dish.getRestaurant());
    }


}
