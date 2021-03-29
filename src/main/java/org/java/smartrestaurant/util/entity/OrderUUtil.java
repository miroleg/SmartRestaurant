package org.java.smartrestaurant.util.entity;

import lombok.NoArgsConstructor;
import org.java.smartrestaurant.dto.OrderUDto;
import org.java.smartrestaurant.model.OrderU;
import org.java.smartrestaurant.service.restaurant.RestaurantService;
import org.java.smartrestaurant.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class OrderUUtil implements EntityUtil<OrderU, OrderUDto> {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    public OrderUDto createDtoFromEntity(OrderU objectEntity) {
        return new OrderUDto(objectEntity.getId(),
                objectEntity.getDateord(),
                objectEntity.getUser().getId(),
                objectEntity.getRestaurant().getId(),
                objectEntity.getTotalCookingTime());
    }


    public OrderU createEntityFromDto(OrderUDto orderUDto) {
        return new OrderU(orderUDto.getId(),
                orderUDto.getDate(),
                userService.read(orderUDto.getUser_id()),
                restaurantService.read(orderUDto.getRestaurant_id()),
                orderUDto.getTotalCookingTime());
    }

    @Override
    public OrderU updateEntityFromDto(OrderU objectEntity, OrderUDto objectDto) {
        return null;
    }


    public OrderU createNewEntityFromAnother(OrderU orderU) {
        return new OrderU(
                orderU.getId(),
                orderU.getDateord(),
                orderU.getUser(),
                orderU.getRestaurant(),
                orderU.getTotalCookingTime());
    }

}
