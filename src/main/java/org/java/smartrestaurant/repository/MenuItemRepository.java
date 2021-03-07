package org.java.smartrestaurant.repository;

import org.java.smartrestaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @Transactional
    @Modifying
    int removeById(int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem")
    void deleteAll();

    @Transactional
    @Modifying
    void deleteAllByDatei(LocalDate date);

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId AND m.datei = :dateParam ORDER BY m.datei DESC")
    List<MenuItem> findByDateAndRestaurant(@Param("restaurantId") int restaurantId,
                                           @Param("dateParam") LocalDate dateParam);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem m WHERE m.restaurant.id = :restaurantId AND m.datei = :dateParam")
    void removeByDateAndRestaurant(@Param("restaurantId") int restaurantId,
                                   @Param("dateParam") LocalDate dateParam);

    @Query("SELECT m FROM MenuItem m LEFT JOIN FETCH m.restaurant LEFT JOIN FETCH m.dish WHERE m.datei = :dateParam ORDER BY m.restaurant ASC")
    List<MenuItem> findByDate(@Param("dateParam") LocalDate dateParam);

}
