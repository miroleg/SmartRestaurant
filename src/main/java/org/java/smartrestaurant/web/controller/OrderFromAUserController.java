package org.java.smartrestaurant.web.controller;


import org.java.smartrestaurant.dto.DishForUserDto;
import org.java.smartrestaurant.dto.OrderFromAUserDto;
import org.java.smartrestaurant.dto.UserDto;
import org.java.smartrestaurant.service.order_item.OrderItemService;
import org.java.smartrestaurant.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;

import static org.java.smartrestaurant.util.entity.UserUtil.createDtoFrom;
import static org.java.smartrestaurant.util.entity.UserUtil.createUserFromDto;

@RestController
@RequestMapping(value = OrderFromAUserController.REST_URL)
public class OrderFromAUserController {
    static final String REST_URL = "/order/register";
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final OrderItemService orderItemService;

    @Autowired
    public OrderFromAUserController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ArrayList<DishForUserDto>> create(@Valid @RequestBody OrderFromAUserDto orderFromAUserDto, UriComponentsBuilder ucBuilder) {
        logger.info("Register new OrderFromAUser with info {}", orderFromAUserDto);/*
      OrderFromAUserDto created = createDtoFrom(orderItemService.create(createDtoListFromEntityList(List<E> objectEntityList)
              createOrderFromDto(orderFromAUserDto)));


/*
public ResponseEntity<OrderFromAUserDto> create(@Valid @RequestBody OrderFromAUserDto orderFromAUserDto, UriComponentsBuilder ucBuilder) {
        logger.info("Register new OrderFromAUser with info {}", orderFromAUserDto);
      OrderFromAUserDto created = createDtoFrom(orderItemService.create(createOrderFromDto(orderFromAUserDto)));


        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/order" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);

 */
        return null;
    }
 /*
 Затем поменяйте контроллер на этот
@Path(value = "/job")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response insertJob(
        Job test
) {
    return Response.ok(test).build();

}
  */


}
