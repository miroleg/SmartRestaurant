package org.java.smartrestaurant.repository;

import org.java.smartrestaurant.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    int removeById(int id);

    @Transactional
    @Query("SELECT d FROM Dish d WHERE LOWER(d.name) LIKE %:name%")
    List<Dish> findByNameIgnoreCaseContaining(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.restaurant.id = :id")
    void removeByRestaurantId(@Param("id") int id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id = :id")
    List<Dish> getDishByRestaurantId(@Param("id") int id);




}
