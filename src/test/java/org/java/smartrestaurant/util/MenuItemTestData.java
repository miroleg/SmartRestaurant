package org.java.smartrestaurant.util;

import org.java.smartrestaurant.model.MenuItem;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.java.smartrestaurant.util.DishTestData.DISHES;
import static org.java.smartrestaurant.util.RestaurantTestData.*;

public class MenuItemTestData {
    private static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    public static final int MENU_ITEM_ID_SEQ = 1;

    public static LocalDate DATE_1 = LocalDate.parse("2018-12-28");
    public static LocalDate DATE_2 = LocalDate.parse("2018-12-29");
    public static LocalDate DATE_3 = LocalDate.parse("2018-12-30");

    /*static {
        try {
            DATE_1 = ft.parse("2018-12-28");
            DATE_2 = ft.parse("2018-12-29");
            DATE_3 = ft.parse("2018-12-30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

    public static int MENU_ITEM_1_RESTAURANT_1_DATE_1_ID = RESTAURANT_ID_SEQ;
    public static int MENU_ITEM_2_RESTAURANT_1_DATE_1_ID = RESTAURANT_ID_SEQ + 1;
    public static int MENU_ITEM_3_RESTAURANT_1_DATE_1_ID = RESTAURANT_ID_SEQ + 2;

    public static int MENU_ITEM_1_RESTAURANT_1_DATE_2_ID = RESTAURANT_ID_SEQ + 3;
    public static int MENU_ITEM_2_RESTAURANT_1_DATE_2_ID = RESTAURANT_ID_SEQ + 4;
    public static int MENU_ITEM_3_RESTAURANT_1_DATE_2_ID = RESTAURANT_ID_SEQ + 5;

    public static int MENU_ITEM_1_RESTAURANT_1_DATE_3_ID = RESTAURANT_ID_SEQ + 6;
    public static int MENU_ITEM_2_RESTAURANT_1_DATE_3_ID = RESTAURANT_ID_SEQ + 7;
    public static int MENU_ITEM_3_RESTAURANT_1_DATE_3_ID = RESTAURANT_ID_SEQ + 8;

    public static int MENU_ITEM_1_RESTAURANT_2_DATE_1_ID = RESTAURANT_ID_SEQ + 9;
    public static int MENU_ITEM_2_RESTAURANT_2_DATE_1_ID = RESTAURANT_ID_SEQ + 10;
    public static int MENU_ITEM_3_RESTAURANT_2_DATE_1_ID = RESTAURANT_ID_SEQ + 11;

    public static int MENU_ITEM_1_RESTAURANT_2_DATE_2_ID = RESTAURANT_ID_SEQ + 12;
    public static int MENU_ITEM_2_RESTAURANT_2_DATE_2_ID = RESTAURANT_ID_SEQ + 13;
    public static int MENU_ITEM_3_RESTAURANT_2_DATE_2_ID = RESTAURANT_ID_SEQ + 14;

    public static int MENU_ITEM_1_RESTAURANT_2_DATE_3_ID = RESTAURANT_ID_SEQ + 15;
    public static int MENU_ITEM_2_RESTAURANT_2_DATE_3_ID = RESTAURANT_ID_SEQ + 16;
    public static int MENU_ITEM_3_RESTAURANT_2_DATE_3_ID = RESTAURANT_ID_SEQ + 17;

    public static int MENU_ITEM_1_RESTAURANT_3_DATE_1_ID = RESTAURANT_ID_SEQ + 18;
    public static int MENU_ITEM_2_RESTAURANT_3_DATE_1_ID = RESTAURANT_ID_SEQ + 19;
    public static int MENU_ITEM_3_RESTAURANT_3_DATE_1_ID = RESTAURANT_ID_SEQ + 20;

    public static int MENU_ITEM_1_RESTAURANT_3_DATE_2_ID = RESTAURANT_ID_SEQ + 21;
    public static int MENU_ITEM_2_RESTAURANT_3_DATE_2_ID = RESTAURANT_ID_SEQ + 22;
    public static int MENU_ITEM_3_RESTAURANT_3_DATE_2_ID = RESTAURANT_ID_SEQ + 23;

    public static int MENU_ITEM_1_RESTAURANT_3_DATE_3_ID = RESTAURANT_ID_SEQ + 24;
    public static int MENU_ITEM_2_RESTAURANT_3_DATE_3_ID = RESTAURANT_ID_SEQ + 25;
    public static int MENU_ITEM_3_RESTAURANT_3_DATE_3_ID = RESTAURANT_ID_SEQ + 26;


    public static int NEW_MENU_ID = RESTAURANT_ID_SEQ + 27;

    public static MenuItem MENU_ITEM_1_RESTAURANT_1_DATE_1 = new MenuItem(MENU_ITEM_1_RESTAURANT_1_DATE_1_ID, DATE_1, RESTAURANT_1, DISHES.get(1), new BigDecimal("10.00"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_1_DATE_1 = new MenuItem(MENU_ITEM_2_RESTAURANT_1_DATE_1_ID, DATE_1, RESTAURANT_1, DISHES.get(2), new BigDecimal("11.00"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_1_DATE_1 = new MenuItem(MENU_ITEM_3_RESTAURANT_1_DATE_1_ID, DATE_1, RESTAURANT_1, DISHES.get(3), new BigDecimal("12.00"));

    public static MenuItem MENU_ITEM_1_RESTAURANT_1_DATE_2 = new MenuItem(MENU_ITEM_1_RESTAURANT_1_DATE_2_ID, DATE_2, RESTAURANT_1, DISHES.get(4), new BigDecimal("10.00"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_1_DATE_2 = new MenuItem(MENU_ITEM_2_RESTAURANT_1_DATE_2_ID, DATE_2, RESTAURANT_1, DISHES.get(5), new BigDecimal("11.00"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_1_DATE_2 = new MenuItem(MENU_ITEM_3_RESTAURANT_1_DATE_2_ID, DATE_2, RESTAURANT_1, DISHES.get(1), new BigDecimal("12.00"));

    public static MenuItem MENU_ITEM_1_RESTAURANT_1_DATE_3 = new MenuItem(MENU_ITEM_1_RESTAURANT_1_DATE_3_ID, DATE_3, RESTAURANT_1, DISHES.get(2), new BigDecimal("10.00"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_1_DATE_3 = new MenuItem(MENU_ITEM_2_RESTAURANT_1_DATE_3_ID, DATE_3, RESTAURANT_1, DISHES.get(3), new BigDecimal("11.00"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_1_DATE_3 = new MenuItem(MENU_ITEM_3_RESTAURANT_1_DATE_3_ID, DATE_3, RESTAURANT_1, DISHES.get(4), new BigDecimal("12.00"));

    public static MenuItem MENU_ITEM_1_RESTAURANT_2_DATE_1 = new MenuItem(MENU_ITEM_1_RESTAURANT_2_DATE_1_ID, DATE_1, RESTAURANT_2, DISHES.get(6), new BigDecimal("11.11"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_2_DATE_1 = new MenuItem(MENU_ITEM_2_RESTAURANT_2_DATE_1_ID, DATE_1, RESTAURANT_2, DISHES.get(7), new BigDecimal("12.99"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_2_DATE_1 = new MenuItem(MENU_ITEM_3_RESTAURANT_2_DATE_1_ID, DATE_1, RESTAURANT_2, DISHES.get(8), new BigDecimal("13.44"));

    public static MenuItem MENU_ITEM_1_RESTAURANT_2_DATE_2 = new MenuItem(MENU_ITEM_1_RESTAURANT_2_DATE_2_ID, DATE_2, RESTAURANT_2, DISHES.get(9), new BigDecimal("12.00"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_2_DATE_2 = new MenuItem(MENU_ITEM_2_RESTAURANT_2_DATE_2_ID, DATE_2, RESTAURANT_2, DISHES.get(10), new BigDecimal("12.33"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_2_DATE_2 = new MenuItem(MENU_ITEM_3_RESTAURANT_2_DATE_2_ID, DATE_2, RESTAURANT_2, DISHES.get(11), new BigDecimal("13.88"));

    public static MenuItem MENU_ITEM_1_RESTAURANT_2_DATE_3 = new MenuItem(MENU_ITEM_1_RESTAURANT_2_DATE_3_ID, DATE_3, RESTAURANT_2, DISHES.get(12), new BigDecimal("14.00"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_2_DATE_3 = new MenuItem(MENU_ITEM_2_RESTAURANT_2_DATE_3_ID, DATE_3, RESTAURANT_2, DISHES.get(13), new BigDecimal("13.00"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_2_DATE_3 = new MenuItem(MENU_ITEM_3_RESTAURANT_2_DATE_3_ID, DATE_3, RESTAURANT_2, DISHES.get(6), new BigDecimal("18.00"));

    public static MenuItem MENU_ITEM_1_RESTAURANT_3_DATE_1 = new MenuItem(MENU_ITEM_1_RESTAURANT_3_DATE_1_ID, DATE_1, RESTAURANT_3, DISHES.get(14), new BigDecimal("22.00"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_3_DATE_1 = new MenuItem(MENU_ITEM_2_RESTAURANT_3_DATE_1_ID, DATE_1, RESTAURANT_3, DISHES.get(15), new BigDecimal("23.00"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_3_DATE_1 = new MenuItem(MENU_ITEM_3_RESTAURANT_3_DATE_1_ID, DATE_1, RESTAURANT_3, DISHES.get(16), new BigDecimal("24.00"));

    public static MenuItem MENU_ITEM_1_RESTAURANT_3_DATE_2 = new MenuItem(MENU_ITEM_1_RESTAURANT_3_DATE_2_ID, DATE_2, RESTAURANT_3, DISHES.get(17), new BigDecimal("25.00"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_3_DATE_2 = new MenuItem(MENU_ITEM_2_RESTAURANT_3_DATE_2_ID, DATE_2, RESTAURANT_3, DISHES.get(18), new BigDecimal("26.00"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_3_DATE_2 = new MenuItem(MENU_ITEM_3_RESTAURANT_3_DATE_2_ID, DATE_2, RESTAURANT_3, DISHES.get(19), new BigDecimal("27.00"));

    public static MenuItem MENU_ITEM_1_RESTAURANT_3_DATE_3 = new MenuItem(MENU_ITEM_1_RESTAURANT_3_DATE_3_ID, DATE_3, RESTAURANT_3, DISHES.get(20), new BigDecimal("28.00"));
    public static MenuItem MENU_ITEM_2_RESTAURANT_3_DATE_3 = new MenuItem(MENU_ITEM_2_RESTAURANT_3_DATE_3_ID, DATE_3, RESTAURANT_3, DISHES.get(21), new BigDecimal("29.00"));
    public static MenuItem MENU_ITEM_3_RESTAURANT_3_DATE_3 = new MenuItem(MENU_ITEM_3_RESTAURANT_3_DATE_3_ID, DATE_3, RESTAURANT_3, DISHES.get(22), new BigDecimal("39.00"));

    public static MenuItem NEW_MENU_ID_ITEM_23_RESTAURANT_3_DATE_3 = new MenuItem(NEW_MENU_ID, DATE_3, RESTAURANT_3, DISHES.get(24), new BigDecimal("77.00"));




    public static List<MenuItem> MENU = Arrays.asList(
            MENU_ITEM_1_RESTAURANT_1_DATE_1,
            MENU_ITEM_2_RESTAURANT_1_DATE_1,
            MENU_ITEM_3_RESTAURANT_1_DATE_1,
            MENU_ITEM_1_RESTAURANT_1_DATE_2,
            MENU_ITEM_2_RESTAURANT_1_DATE_2,
            MENU_ITEM_3_RESTAURANT_1_DATE_2,
            MENU_ITEM_1_RESTAURANT_1_DATE_3,
            MENU_ITEM_2_RESTAURANT_1_DATE_3,
            MENU_ITEM_3_RESTAURANT_1_DATE_3,
            MENU_ITEM_1_RESTAURANT_2_DATE_1,
            MENU_ITEM_2_RESTAURANT_2_DATE_1,
            MENU_ITEM_3_RESTAURANT_2_DATE_1,
            MENU_ITEM_1_RESTAURANT_2_DATE_2,
            MENU_ITEM_2_RESTAURANT_2_DATE_2,
            MENU_ITEM_3_RESTAURANT_2_DATE_2,
            MENU_ITEM_1_RESTAURANT_2_DATE_3,
            MENU_ITEM_2_RESTAURANT_2_DATE_3,
            MENU_ITEM_3_RESTAURANT_2_DATE_3,
            MENU_ITEM_1_RESTAURANT_3_DATE_1,
            MENU_ITEM_2_RESTAURANT_3_DATE_1,
            MENU_ITEM_3_RESTAURANT_3_DATE_1,
            MENU_ITEM_1_RESTAURANT_3_DATE_2,
            MENU_ITEM_2_RESTAURANT_3_DATE_2,
            MENU_ITEM_3_RESTAURANT_3_DATE_2,
            MENU_ITEM_1_RESTAURANT_3_DATE_3,
            MENU_ITEM_2_RESTAURANT_3_DATE_3,
            MENU_ITEM_3_RESTAURANT_3_DATE_3
    );











    public static final int RESTAURANT_2_ID = RESTAURANT_ID_SEQ + 1;


}
