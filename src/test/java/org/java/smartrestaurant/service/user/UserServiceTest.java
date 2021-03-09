package org.java.smartrestaurant.service.user;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.User;
import org.java.smartrestaurant.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.java.smartrestaurant.util.UserTestData.*;
import static org.java.smartrestaurant.util.entity.UserUtil.createUserFromAnother;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void beforeEach() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void create() {
        TestUtil.assertMatch(service.create(createUserFromAnother(USER_5)), USER_5, "registered", "roles", "password");
    }

    @Test
    public void read() {
        TestUtil.assertMatch(service.read(1), USER_1, "registered", "roles", "password");
    }

    @Test
    public void readAll() {
        TestUtil.assertMatch(service.readAll(), USERS, "registered", "roles", "password");
    }

    @Test(expected = NotFoundException.class)
    public void readNotFound() throws Exception {
        service.read(USER_5_ID);
    }

    @Test
    public void update() {
        String name = "mick1";
        User user = service.read(USER_1_ID);
        user.setName(name);
        assertEquals(name, service.update(user).getName());
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(USER_5);
    }

    @Test
    public void delete() {
        service.delete(USER_1_ID);
        List<User> actual = service.readAll();
        List<User> expected = new ArrayList<>(USERS.subList(1, 4));
        TestUtil.assertMatch(actual, expected,"registered", "roles", "password");
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(USER_5_ID);
    }

    @Test
    public void deleteAll() {
        service.deleteAll();
        assertTrue(service.readAll().isEmpty());
    }

}