package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.dto.UserDto;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.service.user.UserService;
import org.java.smartrestaurant.util.entity.UserUtil;
import org.java.smartrestaurant.util.json.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.java.smartrestaurant.util.RoleTestData.ROLE_2_ID;
import static org.java.smartrestaurant.util.TestUtil.assertMatch;
import static org.java.smartrestaurant.util.UserTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserAdminControllerTest extends AbstractControllerTest {
    private String REST_URL = "/admin/users";

    @Autowired
    private UserService userService;

    @Autowired
    private JsonUtil util;

    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + USER_1_ID))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void readOne() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + USER_1_ID))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(util.readFromJsonMvcResult(result, UserDto.class),
                        UserUtil.createDtoFrom(userService.read(USER_1_ID))));
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void readAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(util.getToMatcher(UserUtil.createDtoListFromUserList(userService.readAll())));
    }
/*
    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void readAllSortName() throws Exception {
        List<UserDto> expected = UserUtil.createDtoListFromUserList(userService.readAllOrderByName());
        mockMvc.perform(get(REST_URL + "/sort")
                .param("order", "name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(util.getToMatcher(expected));
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void readAllSortEmail() throws Exception {
        List<UserDto> expected = UserUtil.createDtoListFromUserList(userService.readAllOrderByEmail());
        mockMvc.perform(get(REST_URL + "/sort")
                .param("order", "email"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(util.getToMatcher(expected));
    }


 */
    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void readWithFilter() throws Exception {
        List<UserDto> expected = UserUtil.createDtoListFromUserList(userService.readByNameLike("aL"));
        mockMvc.perform(get(REST_URL + "/filter")
                .param("name", "al"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(util.getToMatcher(expected));
    }
/*
    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void readPage() throws Exception {
        List<UserDto> expected = UserUtil.createDtoListFromUserList(userService.readPaginated(1, 2));
        mockMvc.perform(get(REST_URL + "/page")
                .param("offset", "1")
                .param("size", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(util.getToMatcher(expected));
    }


 */
    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void create() throws Exception {
        UserDto expected = UserUtil.createDtoFrom(USER_5);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(util.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isCreated());
        UserDto actual = util.readFromJsonResultActions(action, UserDto.class);
        assertMatch(actual, expected, "password", "registered", "roles");
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void setEnable() throws Exception {
        mockMvc.perform(post(REST_URL + "/" + USER_2_ID)
                .param("enabled", "false"))
                .andDo(print());
        Assert.assertFalse(userService.read(USER_2_ID).isEnabled());
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void update() throws Exception {
        UserDto expected = UserUtil.createDtoFrom(USER_2);
        expected.setEmail("updated" + USER_2.getEmail());
        ResultActions action = mockMvc.perform(put(REST_URL + "/" + USER_2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(util.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isOk());
        UserDto actual = util.readFromJsonResultActions(action, UserDto.class);
        assertMatch(actual, expected, "password", "registered", "roles");
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void addRole() throws Exception {
        mockMvc.perform(post(REST_URL + "/" + USER_1_ID + "/roles/" + ROLE_2_ID))
                .andDo(print())
                .andExpect(status().isCreated());
        Assert.assertTrue(userService.read(USER_1_ID).getRoles().stream().filter(el -> el.getId().equals(ROLE_2_ID)).count() > 0);
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void deleteRole() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + USER_2_ID + "/roles/" + ROLE_2_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assert.assertEquals(0, userService.read(USER_2_ID).getRoles().stream().filter(el -> el.getId().equals(ROLE_2_ID)).count());
    }

    @Test
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void deleteAllUsers() throws Exception {
        mockMvc.perform(delete(REST_URL ))
                .andDo(print())
                .andExpect(status().isNoContent());
        Assert.assertTrue(userService.readAll().isEmpty());
    }

    @Test(expected = NotFoundException.class)
    @WithMockUser(username = "alex", password = "qwerty2", roles = {"USER", "ADMIN"})
    public void deleteOne() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + USER_3_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        userService.read(USER_3_ID);
    }

}