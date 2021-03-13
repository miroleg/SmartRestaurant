package org.java.smartrestaurant.repository;

import org.java.smartrestaurant.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Transactional
    @Modifying
    int removeById(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderItem ")
    void deleteAll();

    @Transactional
    @Modifying
    void deleteAllByDateo(LocalDate date);

    @Query("SELECT m FROM OrderItem m WHERE m.restaurant.id = :restaurantId AND m.dateo = :dateParam ORDER BY m.dateo DESC")
    List<OrderItem> findByDateAndRestaurant(@Param("restaurantId") int restaurantId,
                                           @Param("dateParam") LocalDate dateParam);

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderItem m WHERE m.restaurant.id = :restaurantId AND m.dateo = :dateParam")
    void removeByDateAndRestaurant(@Param("restaurantId") int restaurantId,
                                   @Param("dateParam") LocalDate dateParam);

    @Query("SELECT m FROM OrderItem m LEFT JOIN FETCH m.restaurant LEFT JOIN FETCH m.dish WHERE m.dateo = :dateParam ORDER BY m.restaurant ASC")
    List<OrderItem> findByDate(@Param("dateParam") LocalDate dateParam);

}
