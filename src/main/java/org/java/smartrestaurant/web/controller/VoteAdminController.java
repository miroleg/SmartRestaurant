package org.java.smartrestaurant.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.java.smartrestaurant.dto.VoteDto;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.service.vote.VoteService;
import org.java.smartrestaurant.util.entity.VoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteAdminController.REST_URL)
public class VoteAdminController {
    private static final Logger logger = LoggerFactory.getLogger(VoteAdminController.class);
    static final String REST_URL = "/admin/votes";

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteUtil voteUtil;

    @Autowired
    ObjectMapper mapper;

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAllOrByDate(@RequestParam(value = "date", required = false) LocalDate date) {
        if (date == null) {
            logger.info("Delete all votes");
            voteService.deleteAll();
        } else {
            logger.info("Delete by date");
            if (!voteService.existsVotesByDate(date)) {
                throw new NotFoundException("No votes found for this date.");
            }
            voteService.removeByDate(date);
        }
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<VoteDto> getAllByDate(@RequestParam(value = "date", required = false) LocalDate date) {
        if (date == null) {
            logger.info("Get all votes");
            return voteUtil.createDtoListFromEntityList(voteService.readAll());
        } else {
            if (!voteService.existsVotesByDate(date)) {
                throw new NotFoundException("No votes found for this date.");
            }
            return voteUtil.createDtoListFromEntityList(voteService.findVotesByDate(date));
        }


    }

}
