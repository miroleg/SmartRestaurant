package org.java.smartrestaurant.repository;

import org.java.smartrestaurant.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

    @Transactional
    @Modifying
    int removeById(int id);
}
