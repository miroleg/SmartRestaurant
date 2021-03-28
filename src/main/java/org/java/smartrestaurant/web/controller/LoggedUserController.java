package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.dto.DishForUserDto;
import org.java.smartrestaurant.dto.OrderItemAdminDto;
import org.java.smartrestaurant.exception.AuthorizationFailedException;
import org.java.smartrestaurant.exception.InvalidOldPasswordException;
import org.java.smartrestaurant.model.*;
import org.java.smartrestaurant.service.order_item.OrderItemService;
import org.java.smartrestaurant.service.restaurant.RestaurantService;
import org.java.smartrestaurant.service.user.UserService;
import org.java.smartrestaurant.service.vote.VoteService;
import org.java.smartrestaurant.util.entity.OrderItemAdminUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping(value = LoggedUserController.REST_URL)
@EnableWebSecurity
public class LoggedUserController {
    private static final Logger logger = LoggerFactory.getLogger(LoggedUserController.class);
    static final String REST_URL = "/profile";

    @Autowired
    private VoteService voteService;

    @Autowired
    private OrderItemAdminUtil orderItemAdminUtil;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${smartrestaurant.app.expiredTime}")
    private Integer expiredTime;


    @PostMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer doOrder(@RequestBody org.java.smartrestaurant.dto.MenuForUserDto inDto) {
        logger.info("Do order");
        LocalDateTime dateTime = LocalDateTime.now();
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            logger.info("Authorization required");
            return -1;
/*            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Content-Type", "text/json; charset=utf-8")
                    .body("{\"status\":\"UNAUTHORIZED\",\"message\":\"Authorization required\",\"errors\":[\"Authorization required\"]}\n");
 */
        }
        logger.info("Do order - Current user name: " + authentication.getName());

        User user = userService.readByName(authentication.getName());

        OrderU orderU = new OrderU(4, dateTime.toLocalDate(), user, restaurantService.read(inDto.getRestaurant().getId()),0);
        logger.info("Create new order :" + orderU.getRestaurant().getId());
        int totalCookingTime = 0;
        for (DishForUserDto dish : inDto.getDishes()) {
            totalCookingTime = totalCookingTime + dish.getDuration();
            logger.info("Create new order item ");
 /*         LocalDate dateFromPath = date;
            LocalDate dateFromDto = dto.getDate();
            if (!dateFromDto.equals(dateFromPath)) {
                throw new NotFoundException("Dates must be the same.");
            }

  */
 //           OrderItemAdminDto orderItemAdminDto = new OrderItemAdminDto(0, dateTime.toLocalDate(), orderU.getId(), orderU.getRestaurant().getId(), dish.getId(), dish.getPrice());
            OrderItemAdminDto orderItemAdminDto = new OrderItemAdminDto(0, dateTime.toLocalDate(),orderU.getId(), orderU.getRestaurant().getId(), dish.getId(), dish.getPrice());

            logger.info("new order item  " + orderItemAdminDto.toString());

            OrderItem entityFromDto = orderItemAdminUtil.createEntityFromDto(orderItemAdminDto);
            logger.info("Create new order item  3");
            orderItemService.create(entityFromDto);
            logger.info("Create new order item  4");
        }
        orderU.setTotalCookingTime(totalCookingTime);

        logger.info("Do order - Current totalCookingTime: " + totalCookingTime);

        return totalCookingTime;
    }



    @PostMapping("/vote")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity doVote(@RequestParam(value = "restaurant", required = true) int restaurantId) {
        logger.info("Do vote");
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime expiredDateTime = LocalDateTime.now().with(LocalTime.of(expiredTime, 0));
        /*if (dateTime.isAfter(expiredDateTime)) {
            throw new TimeExpiredExeption("User can vote no later than " + expiredTime + "  am");
        }*/

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            logger.info("Authorization required");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Content-Type", "text/json; charset=utf-8")
                    .body("{\"status\":\"UNAUTHORIZED\",\"message\":\"Authorization required\",\"errors\":[\"Authorization required\"]}\n");
        }
        logger.info("doVote - Current user name: " + authentication.getName());

        User user = userService.readByName(authentication.getName());
        voteService.removeByDateAndUserId(LocalDate.now(), user.getId());
        voteService.create(new Vote(0, dateTime.toLocalDate(), user, restaurantService.read(restaurantId)));
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "text/json; charset=utf-8")
                .body("{\"status\":\"Vote saved\"}");
    }




    @PostMapping(value = "/update_password")
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePassword(@RequestParam("userId") int userId,
                               @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            logger.info("Authorization required");
            throw new AuthorizationFailedException(); // сознательно сделано по-разному в этих 2-х методах
        }

        User currentUser = userService.readByName(authentication.getName());
        logger.info("UserId: " + userId + " userName: " + authentication.getName()
                + " oldPassword: " + oldPassword + " newPassword: " + newPassword);
        String dbPassword = currentUser.getPassword();
        if (null != oldPassword)
            if (encoder.matches(oldPassword, dbPassword)) {
                if (newPassword != null && !newPassword.isEmpty()) {
                    userService.updatePassword(currentUser, oldPassword, newPassword);
                }
            } else {
                throw new InvalidOldPasswordException();
            }
    }


}
