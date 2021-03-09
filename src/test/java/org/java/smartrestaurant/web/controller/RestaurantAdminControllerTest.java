package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.dto.DishDto;
import org.java.smartrestaurant.dto.MenuItemAdminDto;
import org.java.smartrestaurant.dto.RestaurantDto;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.service.dish.DishService;
import org.java.smartrestaurant.service.menu_item.MenuItemService;
import org.java.smartrestaurant.service.restaurant.RestaurantService;
import org.java.smartrestaurant.util.DateTimeUtil;
import org.java.smartrestaurant.util.entity.DishUtil;
import org.java.smartrestaurant.util.entity.MenuItemAdminUtil;
import org.java.smartrestaurant.util.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.java.smartrestaurant.util.DishTestData.DISHES;
import static org.java.smartrestaurant.util.RestaurantTestData.*;
import static org.java.smartrestaurant.util.TestUtil.assertMatch;
import static org.java.smartrestaurant.util.entity.RestaurantUtil.createDtoFrom;
import static org.java.smartrestaurant.util.entity.RestaurantUtil.createDtoListFromRestaurantList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantAdminControllerTest extends AbstractControllerTest {
    private final String REST_URL = "/admin/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private JsonUtil util;

    @Autowired
    private MenuItemAdminUtil menuItemAdminUtil;

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void getOne() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + RESTAURANT_1_ID))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(util.readFromJsonMvcResult(result, RestaurantDto.class),
                        createDtoFrom(restaurantService.read(RESTAURANT_1_ID))));

    }

    @Test(expected = NotFoundException.class)
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void getOneNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + RESTAURANT_6_ID))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(util.readFromJsonMvcResult(result, RestaurantDto.class),
                        createDtoFrom(restaurantService.read(RESTAURANT_6_ID))));

    }


    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void readAll() throws Exception {
        List<RestaurantDto> expected = createDtoListFromRestaurantList(restaurantService.readAll());
        MvcResult result = mockMvc.perform(get(REST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        List<RestaurantDto> actual = util.readListFromJsonMvcResult(result, RestaurantDto.class);
        assertMatch(actual, expected);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void update() throws Exception {
        RestaurantDto expected = createDtoFrom(RESTAURANT_2);
        expected.setName("updated" + expected.getName());
        ResultActions action = mockMvc.perform(put(REST_URL + "/" + RESTAURANT_2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(util.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isOk());
        RestaurantDto actual = util.readFromJsonResultActions(action, RestaurantDto.class);
        assertMatch(actual, expected);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void create() throws Exception {
        RestaurantDto expected = createDtoFrom(RESTAURANT_6);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(util.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isCreated());
        RestaurantDto actual = util.readFromJsonResultActions(action, RestaurantDto.class);
        assertMatch(actual, expected);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void deleteAll() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assert.assertTrue(restaurantService.readAll().isEmpty());
    }

    @Test(expected = NotFoundException.class)
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void deleteOne() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + RESTAURANT_2_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        restaurantService.read(RESTAURANT_2_ID);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void getDishes() throws Exception {
        List<DishDto> expected = DishUtil.createDtoListFromDishList(new ArrayList(restaurantService.read(RESTAURANT_1_ID).getDishes()));
        MvcResult result = mockMvc.perform(get(REST_URL + "/" + RESTAURANT_1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        List<DishDto> actual = util.readListFromJsonMvcResult(result, DishDto.class);
        assertMatch(actual, expected);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void createDish() throws Exception {
        DishDto expected = new DishDto(0, "New Dish", "New Description");
        ResultActions action = mockMvc.perform(post(REST_URL + "/" + RESTAURANT_2_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(util.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isCreated());
        DishDto actual = util.readFromJsonResultActions(action, DishDto.class);
        expected.setId(actual.getId());
        assertMatch(actual, expected);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void deleteDishes() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + RESTAURANT_2_ID + "/dishes"))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assert.assertTrue(restaurantService.read(RESTAURANT_2_ID).getDishes().isEmpty());
    }

    @Test(expected = NotFoundException.class)
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void deleteOneDish() throws Exception {
        int dishId = 3;
        mockMvc.perform(delete(REST_URL + "/" + RESTAURANT_1_ID + "/dishes/" + dishId))
                .andDo(print())
                .andExpect(status().isNoContent());
        dishService.read(dishId);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void getDishById() throws Exception {
        int dishId = 3;
        mockMvc.perform(get(REST_URL + "/" + RESTAURANT_1_ID + "/dishes/" + dishId))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(util.readFromJsonMvcResult(result, DishDto.class),
                        DishUtil.createDtoFrom(dishService.read(dishId))));
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void getDishByIdNotFound() throws Exception {
        int dishId = 3000;
        mockMvc.perform(get(REST_URL + "/" + RESTAURANT_1_ID + "/dishes/" + dishId))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Entity not found")))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void updateDish() throws Exception {
        int dishId = 2;
        DishDto expected = DishUtil.createDtoFrom(DISHES.get(dishId));
        expected.setName("updated" + expected.getName());
        ResultActions action = mockMvc.perform(put(REST_URL + "/" + RESTAURANT_1_ID + "/dishes/" + dishId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(util.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isOk());
        DishDto actual = util.readFromJsonResultActions(action, DishDto.class);
        assertMatch(actual, expected);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void readMenu() throws Exception {
        List<MenuItemAdminDto> expected =
                menuItemAdminUtil.createDtoListFromEntityList(menuItemService.readByDateAndRestaurant(RESTAURANT_1_ID,
                        DateTimeUtil.getParseDateString(DATE_PART)));
        MvcResult result = mockMvc.perform(get(REST_URL + "/" + RESTAURANT_1_ID + "/menu/menuitems")
                .param("date", DATE_PART))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        List<MenuItemAdminDto> actual = util.readListFromJsonMvcResult(result, MenuItemAdminDto.class);
        assertMatch(actual, expected);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void deleteMenuItemsOnDate() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + RESTAURANT_1_ID + "/menu/menuitems")
                .param("date", DATE_PART))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assert.assertTrue(menuItemService.readByDateAndRestaurant(RESTAURANT_1_ID, DateTimeUtil.getParseDateString(DATE_PART)).isEmpty());
    }




}