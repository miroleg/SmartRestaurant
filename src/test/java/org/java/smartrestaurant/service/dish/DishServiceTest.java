package org.java.smartrestaurant.service.dish;


import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Dish;
import org.java.smartrestaurant.util.entity.DishUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.java.smartrestaurant.util.DishTestData.*;
import static org.java.smartrestaurant.util.TestUtil.assertMatch;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DishServiceTest {

    @Autowired
    private DishService dishService;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void beforeEach() throws Exception {
        cacheManager.getCache("restaurants").clear();
        cacheManager.getCache("dishes").clear();
    }

    @Test
    public void create() {
        Dish dish = DishUtil.createNewFromAnother(NEW_DISH);
        assertMatch(dishService.create(dish),
                NEW_DISH, "restaurant");
    }

    @Test
    public void read() {
        int id = 2;
        assertMatch(dishService.read(id), DISHES.get(id), "restaurant");
    }

    @Test(expected = NotFoundException.class)
    public void readNotFound() {
        assertMatch(dishService.read(NEW_DISH_ID), NEW_DISH, "restaurant");
    }

    @Test
    public void readAll() {
        assertMatch(dishService.readAll(), DISHES.values(), "restaurant");
    }

    @Test
    public void readByNameLike() {
        assertEquals(2, dishService.readByNameLike("chocolat").size());
    }

/*    @Test
    public void readPaginated() {
        assertMatch(dishService.readPaginated(2, 3),
                Arrays.asList(DISHES.get(7), DISHES.get(8), DISHES.get(9)), "restaurant");
    }


 */
    @Test
    public void update() {
        int id = 2;
        String name = DISHES.get(id).getName() + "UPDATED";
        Dish dish = dishService.read(id);
        dish.setName(name);
        assertEquals(dishService.update(dish).getName(), name);
    }

    @Test
    public void delete() {
        int id = 3;
        dishService.delete(DISHES.get(id).getId());
        assertMatch(dishService.readAll(),
                DISHES.values().stream().filter(el -> el.getId()!= id).collect(Collectors.toList()), "restaurant");
    }

    @Test
    public void deleteAll() {
        dishService.deleteAll();
        assertTrue(dishService.readAll().isEmpty());
    }

    @Test
    public void deleteAllByRestaurantId() {
        int id = 2;
        dishService.deleteAllByRestaurantId(id);
        assertMatch(dishService.readAll(), DISHES.values()
                .stream()
                .filter(el -> el.getRestaurant().getId() != id)
                .collect(Collectors.toList()), "restaurant");
    }
}