package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.dto.MenuDtoForUser;
import org.java.smartrestaurant.dto.OrderDtoFromrUser;
import org.java.smartrestaurant.dto.ResultObject;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.repository.VoteRepository;
import org.java.smartrestaurant.service.menu_item.MenuItemService;
import org.java.smartrestaurant.service.order_item.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = PublicController.REST_URL)
@ResponseStatus(value = HttpStatus.FOUND)
public class PublicController {
    private static final Logger logger = LoggerFactory.getLogger(PublicController.class);
    static final String REST_URL = "/public";
    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping(value = "/menu")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MenuDtoForUser> getMenu(@RequestParam("date") LocalDate date) {
        logger.info("Get the menu for the specified date");
        return menuItemService.getMenuForDate(date);
    }


    @GetMapping(value = "/votes/votingresults")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ResultObject> getVotesResults(@RequestParam("date") LocalDate date) {
        logger.info("Get voting  result - restaurants and voices for this date");
        List<ResultObject> resultByDate = voteRepository.getResultByDate(date);
        if (resultByDate.isEmpty()) {
            throw new NotFoundException("No voting results found for this date");
        }
        return resultByDate
                .stream().sorted((el1, el2) -> -el1.getVotes().compareTo(el2.getVotes()))
                .collect(Collectors.toList());

    }


}
