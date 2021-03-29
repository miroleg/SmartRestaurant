package org.java.smartrestaurant.util.entity;

import lombok.NoArgsConstructor;
import org.java.smartrestaurant.dto.OrderItemAdminDto;
import org.java.smartrestaurant.model.Dish;
import org.java.smartrestaurant.model.OrderItem;
import org.java.smartrestaurant.model.Restaurant;
import org.java.smartrestaurant.service.dish.DishService;
import org.java.smartrestaurant.service.order_u.OrderUService;
import org.java.smartrestaurant.service.order_item.OrderItemService;
import org.java.smartrestaurant.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class OrderItemAdminUtil implements EntityUtil<OrderItem, OrderItemAdminDto> {
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderUService orderUService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Override
    public OrderItemAdminDto createDtoFromEntity(OrderItem objectEntity) {
        return new OrderItemAdminDto(objectEntity.getId(), objectEntity.getDateo(),
                objectEntity.getOrderU().getId(), objectEntity.getRestaurant().getId(), objectEntity.getDish().getId(), objectEntity.getPrice());
    }

    @Override
    public OrderItem createEntityFromDto(OrderItemAdminDto orderItemAdminDto) {
        return new OrderItem(orderItemAdminDto.getId(), orderItemAdminDto.getDate(),
                orderUService.read(orderItemAdminDto.getOrderu_id()),
                restaurantService.read(orderItemAdminDto.getRestaurant_id()),
                dishService.read(orderItemAdminDto.getDish_id()),
                orderItemAdminDto.getPrice());
    }

    @Override
    public OrderItem updateEntityFromDto(OrderItem orderItem, OrderItemAdminDto orderItemAdminDto) {
        Restaurant restaurant = restaurantService.read(orderItemAdminDto.getRestaurant_id());
        Dish dish = dishService.read(orderItemAdminDto.getDish_id());
        orderItem.setRestaurant(restaurant);
        orderItem.setDish(dish);
        return orderItemService.update(orderItem);
    }

    @Override
    public OrderItem createNewEntityFromAnother(OrderItem orderItem) {
        return new OrderItem(
                orderItem.getId(),
                orderItem.getDateo(),
                orderItem.getOrderU(),
                orderItem.getRestaurant(),
                orderItem.getDish(),
                orderItem.getPrice());
    }

}
