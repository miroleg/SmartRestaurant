package org.java.smartrestaurant.repository;

import org.java.smartrestaurant.model.OrderU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OrderURepository extends JpaRepository<OrderU, Integer> {
/*
    @Transactional
    @Modifying
    @Query("DELETE FROM Orderfromauser v WHERE v.dateord = :date")
    void removeByDate(@Param("date") LocalDate date);


    @Query("SELECT v FROM Orderfromauser v WHERE v.id = :id")
    OrderFromAUser findById(@Param("id") int id);

    @Query("SELECT v FROM Orderfromauser v WHERE v.dateord = :date")
    List<OrderFromAUser> findOrderFromAUsersByDate(@Param("date") LocalDate date);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Orderfromauser v WHERE v.dateord = :date")
    boolean existsOrderFromAUsersByDate(@Param("date") LocalDate date);

    //https://stackoverflow.com/a/47471486/9632963
    @Query("SELECT new org.java.smartrestaurant.dto.ResultObject(v.restaurant.name, COUNT(v)) FROM Vote v WHERE dateord  = :date GROUP BY v.restaurant.name")
    List<ResultObject> getResultByDate(@Param("date") LocalDate date);




    @Transactional
    @Modifying
    @Query("DELETE FROM Orderfromauser v WHERE v.dateord = :date AND v.user.id = :userId")
    void removeByDateAndUserId(@Param("date") LocalDate date, @Param("userId") int userId);



    @Query("SELECT v FROM Orderfromauser v WHERE v.dateord = :date AND v.user.id = :userId AND v.restaurant.id = :restaurantId")
    OrderFromAUser findOrderFromAUsersByDateAndUserIdAndRestaurantId(
            @Param("date") LocalDate date, @Param("userId") int userId, @Param("restaurantId") int restaurantId);


 */

}
