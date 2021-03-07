package org.java.smartrestaurant.repository;

import org.java.smartrestaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id = :id")
    int removeById(@Param("id") int id);

    @Transactional
    @Override
    @Modifying
    @Query("DELETE FROM Restaurant" )
    void deleteAll();


    @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> findById(@Param("id") int id);

    @Query(value = "SELECT r FROM Restaurant r LEFT JOIN FETCH r.dishes LEFT JOIN FETCH r.menuItems LEFT JOIN FETCH r.votes WHERE r.id = :id ")
    Optional<Restaurant> findByIdWithRelations (@Param("id") int id);


    @Override
    @Query(value = "SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.dishes LEFT JOIN FETCH r.menuItems LEFT JOIN FETCH r.votes")
    List<Restaurant> findAll();


    Restaurant findByName(String name);

}
