package org.java.smartrestaurant.config.security;

import org.java.smartrestaurant.dto.UserDto;
import org.java.smartrestaurant.model.User;
import org.java.smartrestaurant.util.entity.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserDto userTo;

    public AuthorizedUser(User user) {
        super(user.getName(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = UserUtil.createDtoFrom(user);
    }

    public int getId() {
        return userTo.getId();
    }

    public void update(UserDto newDto) {
        userTo = newDto;
    }

    public UserDto getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }


}