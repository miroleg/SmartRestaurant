package org.java.smartrestaurant.service.order_item;


import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.OrderItem;
import org.java.smartrestaurant.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@CacheConfig(cacheNames = "orderItems")
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    @Cacheable("orderItems")
    public List<OrderItem> readByDateAndRestaurant(int restaurantId, LocalDate dateParam) {
        return orderItemRepository.findByDateAndRestaurant(restaurantId, dateParam);

    }

    @Override
    @CacheEvict(value = "orderItems", allEntries = true)
    @Transactional
    public void deleteByDateAndRestaurant(int restaurantId, LocalDate dateParam) {
        orderItemRepository.removeByDateAndRestaurant(restaurantId, dateParam);
    }

    @Override
    @Cacheable("orderItems")
    public List<OrderItem> readByDate(LocalDate dateParam) {
        return orderItemRepository.findByDate(dateParam);
    }

    @Override
    @CacheEvict(value = "orderItems", allEntries = true)
    @Transactional
    public OrderItem create(OrderItem object) {
        Objects.requireNonNull(object, "Parameter object cannot be null");
        if (!object.getDish().getRestaurant().getId().equals(object.getRestaurant().getId())) {
            throw new NotFoundException("Not found dish with id = " + object.getDish().getId() + " for restaurant with id = " + object.getRestaurant().getId());
        }
        object.setId(0);
        return orderItemRepository.save(object);
    }

    @Override
    @Cacheable("orderItems")
    public OrderItem read(int id) throws NotFoundException {
        return orderItemRepository.findById(id).orElseThrow(() -> new NotFoundException("MenuItem with id = " + id + " not found"));
    }

    @Override
    @Cacheable("orderItems")
    public List<OrderItem> readAll() {
        return orderItemRepository.findAll();
    }

    @Override
    @CacheEvict(value = "orderItems", allEntries = true)
    @Transactional
    public OrderItem update(OrderItem object) throws NotFoundException {
        Objects.requireNonNull(object, "Parameter object cannot be null");
        if (!orderItemRepository.existsById(object.getId())) {
            throw new NotFoundException("OrderItem with id = " + object.getId() + " not exists");
        }
        if (!object.getDish().getRestaurant().getId().equals(object.getRestaurant().getId())) {
            throw new NotFoundException("Not found dish with id = " + object.getDish().getId() + " for restaurant with id = " + object.getRestaurant().getId());
        }
        return orderItemRepository.save(object);
    }

    @Override
    @CacheEvict(value = "orderItems", allEntries = true)
    @Transactional
    public void delete(int id) throws NotFoundException {
        if (orderItemRepository.removeById(id) == 0) {
            throw new NotFoundException("MenuItem with id = " + id + " not found");
        }
    }

    @Override
    @CacheEvict(value = "orderItems", allEntries = true)
    @Transactional
    public void deleteAll() {
        orderItemRepository.deleteAll();
    }

 /*
    public List<OrderDtoFromrUser> getOrderForDate(LocalDate date) {
        List<OrderItem> orderItems = readByDate(date);
        if (orderItems.isEmpty()) {
            throw new NotFoundException("No menu found for this date.");
        }
        Map<Restaurant, List<OrderItem>> collect = orderItems.stream()
                .collect(Collectors.groupingBy(OrderItem::getRestaurant));
        List<OrderDtoFromrUser> list = new ArrayList<>();
        collect.entrySet().forEach(el -> {
            List<DishDtoForUser> collect1 = el.getValue().stream().map(el1 -> new DishDtoForUser(el1.getDish().getId(), el1.getDish().getName(),
                    el1.getDish().getDescription(), el1.getPrice())).sorted(Comparator.comparing(DishDtoForUser::getId)).collect(Collectors.toList());
            list.add(new OrderDtoFromrUser(1,date,
                    RestaurantUtil.createDtoFrom(el.getKey()), collect1));
        });

        return list.stream().sorted(Comparator.comparing(el -> el.getRestaurant().getId())).collect(Collectors.toList());

    }


  */

    @Override
    public void deleteAllByDate(LocalDate date) {
        orderItemRepository.deleteAllByDateo(date);
    }
}
