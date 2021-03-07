package org.java.smartrestaurant.util.entity;

import org.java.smartrestaurant.dto.UserDto;
import org.java.smartrestaurant.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class UserUtil {
    public static UserDto createDtoFrom(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(),
                user.getPassword(), user.isEnabled());
    }

    public static List<UserDto> createDtoListFromUserList(List<User> userList) {
        return userList.stream().map(el -> new UserDto(el.getId(), el.getName(), el.getEmail(),
                el.getPassword(), el.isEnabled())).collect(Collectors.toList());
    }

    public static User createUserFromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail(),
                userDto.getPassword(), new Date(), userDto.isEnabled(), Collections.emptyList());
    }

    public static User updateUserFromDto(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail().toLowerCase());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.isEnabled());
        return user;
    }

    public static User createUserFromAnother(User user) {
        return new User(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.getRegistered(), user.isEnabled(), new ArrayList(user.getRoles()));
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        return user;
    }






}
