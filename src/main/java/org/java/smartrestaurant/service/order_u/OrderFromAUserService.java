package org.java.smartrestaurant.service.order_u;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.OrderU;
import org.java.smartrestaurant.service.BaseService;

public interface OrderFromAUserService extends BaseService<OrderU> {
    OrderU readByName(String name) throws NotFoundException;
}
