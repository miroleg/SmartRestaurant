package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.dto.UserDto;
import org.java.smartrestaurant.util.entity.UserUtil;
import org.java.smartrestaurant.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.java.smartrestaurant.util.TestUtil.assertMatch;
import static org.java.smartrestaurant.util.UserTestData.USER_5;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterControllerTest extends AbstractControllerTest {
    private final String REST_URL = "/public/register";

    @Autowired
    private JsonUtil util;

    @Test
    public void create() throws Exception {
        UserDto expected = UserUtil.createDtoFrom(USER_5);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(util.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isCreated());
        UserDto actual = util.readFromJsonResultActions(action, UserDto.class);
        expected.setId(actual.getId());
        assertMatch(actual, expected, "password", "registered", "roles");

    }
}