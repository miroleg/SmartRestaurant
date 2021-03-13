package org.java.smartrestaurant.service.order_item;



import org.java.smartrestaurant.model.OrderItem;
import org.java.smartrestaurant.service.BaseService;

import java.time.LocalDate;
import java.util.List;


public interface OrderItemService extends BaseService<OrderItem> {
    List<OrderItem> readByDateAndRestaurant(int restaurantId, LocalDate dateParam);
    void deleteByDateAndRestaurant(int restaurantId, LocalDate dateParam);
    List<OrderItem> readByDate(LocalDate dateParam);
//    List<OrderDtoFromrUser> getOrderForDate(LocalDate date);     // список счетов по ресторанам на дату
    void deleteAllByDate(LocalDate date);
}
