package org.java.smartrestaurant.service.user;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.User;
import org.java.smartrestaurant.service.BaseService;

import java.util.List;

public interface UserService extends BaseService<User> {
    User readByName(String name) throws NotFoundException;

//    List<User> readAllOrderByName();
//    List<User> readAllOrderByEmail();

    List<User> readByNameLike(String name);

//    List<User> readPaginated(int page, int size);

    User setEnabled(int id, boolean enabled);
    void updatePassword(User user, String oldPassword, String newPassword);


}
