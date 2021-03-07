package org.java.smartrestaurant.repository;

import org.java.smartrestaurant.dto.ResultObject;
import org.java.smartrestaurant.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.datev = :date")
    void removeByDatev(@Param("date") LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.id = :id")
    Vote findById(@Param("id") int id);

    @Query("SELECT v FROM Vote v WHERE v.datev = :date")
    List<Vote> findVotesByDatev(@Param("date") LocalDate date);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Vote v WHERE v.datev = :date")
    boolean existsVotesByDatev(@Param("date") LocalDate date);

    //https://stackoverflow.com/a/47471486/9632963
    @Query("SELECT new org.java.smartrestaurant.dto.ResultObject(v.restaurant.name, COUNT(v)) FROM Vote v WHERE datev  = :date GROUP BY v.restaurant.name")
    List<ResultObject> getResultByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE datev = :date AND v.user.id = :userId")
    void removeByDateAndUserId(@Param("date") LocalDate date, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE datev = :date AND v.user.id = :userId AND v.restaurant.id = :restaurantId")
    Vote findVotesByDateAndUserIdAndRestaurantId(
            @Param("date") LocalDate date, @Param("userId") int userId, @Param("restaurantId") int restaurantId);

}
