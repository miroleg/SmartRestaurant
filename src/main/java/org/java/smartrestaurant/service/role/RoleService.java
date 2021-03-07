package org.java.smartrestaurant.service.role;

import org.java.smartrestaurant.service.BaseService;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Role;

public interface RoleService extends BaseService<Role> {
    Role readByName(String name) throws NotFoundException;

}
