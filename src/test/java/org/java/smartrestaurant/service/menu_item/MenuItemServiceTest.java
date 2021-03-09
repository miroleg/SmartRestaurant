package org.java.smartrestaurant.service.menu_item;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Dish;
import org.java.smartrestaurant.model.MenuItem;
import org.java.smartrestaurant.util.entity.MenuItemAdminUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.java.smartrestaurant.util.DishTestData.DISHES;
import static org.java.smartrestaurant.util.MenuItemTestData.*;
import static org.java.smartrestaurant.util.RestaurantTestData.RESTAURANT_1_ID;
import static org.java.smartrestaurant.util.TestUtil.assertMatch;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuItemServiceTest {
    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private MenuItemAdminUtil menuItemAdminUtil;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void beforeEach() throws Exception {
        cacheManager.getCache("restaurants").clear();
        cacheManager.getCache("dishes").clear();
        cacheManager.getCache("menuItems").clear();
    }


    @Test
    public void readByDateAndRestaurant() {
        assertMatch(menuItemService.readByDateAndRestaurant(1, DATE_1),
                Arrays.asList(MENU_ITEM_1_RESTAURANT_1_DATE_1, MENU_ITEM_2_RESTAURANT_1_DATE_1, MENU_ITEM_3_RESTAURANT_1_DATE_1), "dish", "restaurant");
    }

    @Test
    public void deleteByDateAndRestaurant() {
        menuItemService.deleteByDateAndRestaurant(RESTAURANT_1_ID, DATE_1);
        assertTrue(menuItemService.readByDateAndRestaurant(RESTAURANT_1_ID, DATE_1).isEmpty());
    }

    @Test
    public void readByDate() {
        assertMatch(menuItemService.readByDate(DATE_1), MENU.stream().filter(el -> el.getDatei().equals(DATE_1)).collect(Collectors.toList()), "dish", "restaurant");
    }

    @Test
    public void create() {
        MenuItem newMenuItem = menuItemAdminUtil.createNewEntityFromAnother(NEW_MENU_ID_ITEM_23_RESTAURANT_3_DATE_3);
        assertMatch(menuItemService.create(newMenuItem), NEW_MENU_ID_ITEM_23_RESTAURANT_3_DATE_3, "dish", "restaurant");

    }

    @Test
    public void read() {
        assertMatch(menuItemService.read(MENU_ITEM_2_RESTAURANT_1_DATE_1.getId()), MENU_ITEM_2_RESTAURANT_1_DATE_1, "dish", "restaurant");
    }

    @Test
    public void readAll() {
        List<String> actual = menuItemService.readAll().stream().map(el -> new String(el.getId() + " " + el.getDatei() + " " + el.getDish().getId() + " " + el.getRestaurant().getId())).collect(Collectors.toList());
        List<String> expected = MENU.stream().map(el -> new String(el.getId() + " " + el.getDatei() + " " + el.getDish().getId() + " " + el.getRestaurant().getId())).collect(Collectors.toList());
        assertMatch(actual, expected);

    }

    @Test
    public void update() {
        int dishId = 5;
        Dish updatedDish = DISHES.get(dishId);
        MenuItem menuItem = menuItemService.read(MENU_ITEM_1_RESTAURANT_1_DATE_2_ID);
        menuItem.setDish(updatedDish);
        assertMatch(menuItemService.read(MENU_ITEM_1_RESTAURANT_1_DATE_2_ID).getDish(), updatedDish, "restaurant");
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        menuItemService.delete(MENU_ITEM_1_RESTAURANT_1_DATE_2_ID);
        menuItemService.read(MENU_ITEM_1_RESTAURANT_1_DATE_2_ID);
    }

    @Test
    public void deleteAll() {
        menuItemService.deleteAll();
        assertTrue(menuItemService.readAll().isEmpty());
    }
}