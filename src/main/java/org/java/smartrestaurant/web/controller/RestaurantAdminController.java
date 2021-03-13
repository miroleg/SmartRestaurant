package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.dto.DishDto;
import org.java.smartrestaurant.dto.MenuItemAdminDto;
import org.java.smartrestaurant.dto.RestaurantDto;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Dish;
import org.java.smartrestaurant.model.MenuItem;
import org.java.smartrestaurant.model.Restaurant;
import org.java.smartrestaurant.service.dish.DishService;
import org.java.smartrestaurant.service.menu_item.MenuItemService;
import org.java.smartrestaurant.service.restaurant.RestaurantService;
import org.java.smartrestaurant.util.entity.DishUtil;
import org.java.smartrestaurant.util.entity.MenuItemAdminUtil;
import org.java.smartrestaurant.util.entity.RestaurantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.java.smartrestaurant.util.entity.RestaurantUtil.createDtoListFromRestaurantList;

@RestController
@RequestMapping(value = RestaurantAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminController {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantAdminController.class);
    static final String REST_URL = "/admin/restaurants";
    private final RestaurantService restaurantService;
    private final DishService dishService;
    private final MenuItemService menuItemService;

    @Autowired
    private MenuItemAdminUtil menuItemAdminUtil;

    @Autowired
    public RestaurantAdminController(RestaurantService restaurantService, DishService dishService, MenuItemService menuItemService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.menuItemService = menuItemService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<RestaurantDto> getAll() {
        logger.info("Retrieve all restaurant");
        return createDtoListFromRestaurantList(restaurantService.readAll());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.FOUND)
    public RestaurantDto read(@PathVariable int id) {
        logger.info("Get restaurant with if = {}", id);
        return RestaurantUtil.createDtoFrom(restaurantService.read(id));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public RestaurantDto update(@Valid @RequestBody RestaurantDto restaurantDto) {
        logger.info("Update restaurant {} with info {}", restaurantDto.getId(), restaurantDto);
        Restaurant restaurant = restaurantService.read(restaurantDto.getId());
        return RestaurantUtil.createDtoFrom(restaurantService.update(RestaurantUtil.updateRestaurantFromDto(restaurant, restaurantDto)));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<RestaurantDto> create(@Valid @RequestBody RestaurantDto restaurantDto, UriComponentsBuilder ucBuilder) {
        logger.info("Put new restaurant with info {}", restaurantDto);
        RestaurantDto created = RestaurantUtil.createDtoFrom(restaurantService.create(RestaurantUtil.createRestaurantFromDto(restaurantDto)));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAll() {
        logger.info("Delete all restaurants");
        restaurantService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        logger.info("Delete restaurant with id = {}", id);
        restaurantService.delete(id);
    }

    @GetMapping("/{id}/dishes")
    @ResponseStatus(value = HttpStatus.OK)
    public List<DishDto> getDishes(@PathVariable("id") int id) {
        logger.info("Get dishes for with id = {}", id);
        return DishUtil.createDtoListFromDishList(new ArrayList<Dish>(restaurantService.read(id).getDishes()));
    }

    @PostMapping("/{id}/dishes")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<DishDto> createDish(@Valid @RequestBody DishDto dishDto, @PathVariable("id") int id, UriComponentsBuilder ucBuilder) {
        logger.info("Put new dish with info {} fro restaurant with id = {}", dishDto, id);
        Dish createdDish = DishUtil.createDishFromDto(dishDto);
        createdDish.setRestaurant(restaurantService.read(id));
        createdDish = dishService.create(createdDish);
        DishDto created = DishUtil.createDtoFrom(createdDish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}dishes/")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}/dishes")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDishes(@PathVariable("id") int id) {
        logger.info("Delete all dishes for restaurant with id = {}", id);
        dishService.deleteAllByRestaurantId(id);
    }

    @DeleteMapping("/{id}/dishes/{dishId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDishById(@PathVariable("id") int id, @PathVariable("dishId") int dishId) {
        logger.info("Delete dish with id = {} for restaurant with id = {}", dishId, id);
        Dish dish = dishService.read(dishId);
        int restaurantId = dish.getRestaurant().getId();
        if (restaurantId != id) {
            throw new NotFoundException("Dish with id = " + dishId + " for restaurant with id = " + id + " not found. Found for restaurant with id = " + restaurantId);
        }
        dishService.delete(dishId);
    }

    @GetMapping("/{id}/dishes/{dishId}")
    @ResponseStatus(value = HttpStatus.OK)
    public DishDto getDishById(@PathVariable("id") int id, @PathVariable("dishId") int dishId) {
        logger.info("Get dish with id = {} for restaurant with id = {}", dishId, id);
        Dish dish = dishService.read(dishId);
        int restaurantId = dish.getRestaurant().getId();
        if (dish.getRestaurant().getId() != id) {
            throw new NotFoundException("Dish with id = " + dishId + " for restaurant with id = " + id + " not found. Found for restaurant with id = " + restaurantId);
        }
        return DishUtil.createDtoFrom(dish);
    }

    @PutMapping("/{id}/dishes/{dishId}")
    @ResponseStatus(value = HttpStatus.OK)
    public DishDto updateDish(@Valid @RequestBody DishDto dishDto, @PathVariable("id") int id, @PathVariable("dishId") int dishId) {
        logger.info("Update dish {} with info {}", dishDto.getId(), dishDto);
        Dish dish = dishService.read(dishId);
        int restaurantId = dish.getRestaurant().getId();
        if (dishDto.getId() != dishId || restaurantId != id) {
            throw new NotFoundException("Dish with id = " + dishId + " for restaurant with id = " + id + " not found. Found for restaurant with id = " + restaurantId);
        }
        DishUtil.updateDishFromDto(dish, dishDto);
        Dish updatedDish = dishService.update(dish);
        return DishUtil.createDtoFrom(updatedDish);
    }

    //Working with menu

    @GetMapping("/{id}/menu/menuitems")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MenuItemAdminDto> getMenu(@PathVariable("id") int id,
                                          @RequestParam("date") LocalDate date) {
        logger.info("Get menu");
        List<MenuItem> menuItems = menuItemService.readByDateAndRestaurant(id, date);
        return menuItemAdminUtil.createDtoListFromEntityList(menuItems);
    }

    @DeleteMapping("/{id}/menu/menuitems") //{menuitem_id}
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable("id") int id,
                                          @RequestParam("date") LocalDate date) {
        logger.info("Delete menu by date and restaurant");
        menuItemService.deleteByDateAndRestaurant(id, date);
    }

    @GetMapping("/{id}/menu/menuitems/{menuItemsId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MenuItemAdminDto getMenuItem(@PathVariable("id") int id,
                                          @RequestParam("date") LocalDate date, @PathVariable("menuItemsId") int menuItemsId) {
        logger.info("Get menu item on date");
        MenuItem menuItem = menuItemService.readByDateAndRestaurant(id, date)
                .stream().filter(el -> el.getId().equals(menuItemsId)).findFirst().orElseThrow(() -> new NotFoundException("Not dound menu items with id = " + menuItemsId));
        return menuItemAdminUtil.createDtoFromEntity(menuItem);

    }
    @PostMapping("/{id}/menu/menuitems")
    @ResponseStatus(value = HttpStatus.OK)
    public MenuItemAdminDto addMenuItem(@PathVariable("id") int id,
                                        @RequestParam("date") LocalDate date, @Validated @RequestBody  MenuItemAdminDto dto) {
        logger.info("Create new menu item menu item");
        LocalDate dateFromPath = date;
        LocalDate dateFromDto = dto.getDate();
        if (!dateFromDto.equals(dateFromPath)) {
            throw new NotFoundException("Dates must be the same.");
        }
        MenuItemAdminDto menuItemAdminDto = new MenuItemAdminDto(0, dateFromDto, dto.getRestaurant_id(), dto.getDish_id(), dto.getPrice());
        MenuItem entityFromDto = menuItemAdminUtil.createEntityFromDto(menuItemAdminDto);
        return menuItemAdminUtil.createDtoFromEntity(menuItemService.create(entityFromDto));
    }

    @DeleteMapping("/{id}/menu/menuitems/{menuItemsId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMenuItem(@PathVariable("id") int id,
                                        @RequestParam("date") LocalDate date, @PathVariable("menuItemsId") int menuItemsId) {
        logger.info("Delete menu item");
        MenuItem menuItem = menuItemService.readByDateAndRestaurant(id, date)
                .stream().filter(el -> el.getId().equals(menuItemsId)).findFirst().orElseThrow(() -> new NotFoundException("Not found menu items with id = " + menuItemsId));
        menuItemService.delete(menuItem.getId());
    }

    //Working with order


}
