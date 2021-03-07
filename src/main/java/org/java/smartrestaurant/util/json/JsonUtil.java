package org.java.smartrestaurant.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.java.smartrestaurant.dto.UserDto;
import org.java.smartrestaurant.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@EnableWebMvc
public class JsonUtil {
    @Autowired
    private ObjectMapper mapper;

    public <T> String writeValue(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public <T> T readValue(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public <T> String writeAdditionProps(T obj, String addName, Object addValue) {
        return writeAdditionProps(obj, Map.of(addName, addValue));
    }

    public <T> String writeAdditionProps(T obj, Map<String, Object> addProps) {
        Map<String, Object> map = mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
        map.putAll(addProps);
        return writeValue(map);
    }


    public <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = mapper.readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }


    public <T> T readFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return readValue(getContent(result), clazz);
    }




    public String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    public <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return readValues(getContent(result), clazz);
    }

    public <T> List<T> readListFromJsonString(String json) throws Exception {
        return mapper.readValue(json, new TypeReference<List<T>>(){});

    }

    public <T> ResultMatcher getToMatcher(T... expected) {
        return getToMatcher(List.of(expected));
    }

    public <T> T readFromJsonResultActions(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return readFromJsonMvcResult(action.andReturn(), clazz);
    }


    public ResultMatcher getToMatcher(Iterable<UserDto> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, UserDto.class)).isEqualTo(expected);
    }

    public ResultMatcher getToMatcherRoles(Iterable<Role> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Role.class)).isEqualTo(expected);
    }

}
