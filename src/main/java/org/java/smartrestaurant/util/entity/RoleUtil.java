package org.java.smartrestaurant.util.entity;

import org.java.smartrestaurant.model.Role;

public class RoleUtil {
    public static Role createNewFromAnother(Role role) {
        return new Role(role.getId(), role.getName());
    }
}
