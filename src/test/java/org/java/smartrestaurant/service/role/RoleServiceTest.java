package org.java.smartrestaurant.service.role;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.java.smartrestaurant.util.RoleTestData.*;
import static org.java.smartrestaurant.util.TestUtil.assertMatch;
import static org.java.smartrestaurant.util.entity.RoleUtil.createNewFromAnother;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleServiceTest {
    @Autowired
    private RoleService service;

    @Autowired
    CacheManager cacheManager;

    @Before
    public void beforeEach() throws Exception {
        cacheManager.getCache("users").clear();
        cacheManager.getCache("roles").clear();
    }


    @Test
    public void create() {
        assertMatch(service.create(createNewFromAnother(ROLE_4)), ROLE_4);

    }
/*
    @Test
    public void read() {
        assertMatch(service.read(ROLE_1_ID), ROLE_1);
    }

    @Test
    public void readAll() {
        assertMatch(service.readAll(), ROLES);
    }

 */

    @Test
    public void update() {
        String name = "SPECIALUSER";
        Role role = service.read(ROLE_1_ID);
        role.setName(name);
        assertEquals(name, service.update(role).getName());
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(ROLE_4);
    }

    @Test
    public void delete() {
        service.delete(ROLE_1_ID);
        List<Role> actual = service.readAll();
        List<Role> expected = Arrays.asList(ROLE_2, ROLE_3);
        assertMatch(actual, expected);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(ROLE_4_ID);
    }

    @Test
    public void deleteAll() {
        service.deleteAll();
        assertTrue(service.readAll().isEmpty());
    }

    @Test
    public void readByName() {
        assertMatch(service.readByName("ROLE_ADMIN"), ROLE_2);
    }

    @Test(expected = NotFoundException.class)
    public void readByNameNotFound() {
        assertMatch(service.readByName("SUPERADMIN"), ROLE_2);
    }


}