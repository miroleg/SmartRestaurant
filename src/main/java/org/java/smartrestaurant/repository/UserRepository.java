package org.java.smartrestaurant.repository;

import org.java.smartrestaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    int removeById(int id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r WHERE u.id = :id")
    Optional<User> findById(@Param("id") int id);
//    Optional<User> findById(int id);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles r WHERE UPPER(u.name) LIKE CONCAT('%',UPPER(:name),'%')")
    List<User> findByNameIgnoreCaseContaining(@Param("name") String name);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles r WHERE UPPER(u.name) = UPPER(:name)")
    User findByName(@Param("name") String name);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r")
    List<User> findAll();


}
