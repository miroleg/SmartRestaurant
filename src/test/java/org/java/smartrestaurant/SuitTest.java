package org.java.smartrestaurant;

import org.java.smartrestaurant.service.dish.DishServiceTest;
import org.java.smartrestaurant.service.menu_item.MenuItemServiceTest;
import org.java.smartrestaurant.service.restaurant.RestaurantServiceTest;
import org.java.smartrestaurant.service.role.RoleServiceTest;
import org.java.smartrestaurant.service.user.UserServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DishServiceTest.class,
        MenuItemServiceTest.class,
        RestaurantServiceTest.class,
        RoleServiceTest.class,
        UserServiceTest.class
    }
)


public class SuitTest {
}
