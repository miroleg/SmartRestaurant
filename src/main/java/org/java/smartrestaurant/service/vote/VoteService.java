package org.java.smartrestaurant.service.vote;

import org.java.smartrestaurant.dto.ResultObject;
import org.java.smartrestaurant.model.Vote;
import org.java.smartrestaurant.service.BaseService;

import java.time.LocalDate;
import java.util.List;

public interface VoteService extends BaseService<Vote> {

    //void deleteByDate(LocalDate date);
    //void deleteByUserId(int id);
    //void deleteByRestaurantId(int id);

    //List<Vote> findVotesByDateAndRestaurantId(LocalDate date, int resturantId);
    void removeByDate(LocalDate date);
    List<Vote> findVotesByDate(LocalDate date);
    boolean existsVotesByDate(LocalDate date);
    List<ResultObject> getResultByDate(LocalDate date);
    void removeByDateAndUserId(LocalDate date, int userId);
    Vote findVotesByDateAndUserIdAndRestaurantId(
            LocalDate date, int userId, int restaurantId);
}
