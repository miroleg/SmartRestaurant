package org.java.smartrestaurant.service.menu_item;

import org.java.smartrestaurant.dto.MenuForUserDto;
import org.java.smartrestaurant.model.MenuItem;
import org.java.smartrestaurant.service.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemService extends BaseService<MenuItem> {
    List<MenuItem> readByDateAndRestaurant(int restaurantId, LocalDate dateParam);
    void deleteByDateAndRestaurant(int restaurantId, LocalDate dateParam);
    List<MenuItem> readByDate(LocalDate dateParam);
    List<MenuForUserDto> getMenuForDate(LocalDate date);
    void deleteAllByDate(LocalDate date);


}
