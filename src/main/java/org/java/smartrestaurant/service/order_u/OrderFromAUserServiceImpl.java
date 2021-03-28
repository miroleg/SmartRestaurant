package org.java.smartrestaurant.service.order_u;


import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.OrderU;
import org.java.smartrestaurant.repository.OrderURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
    @CacheConfig(cacheNames = "orderUs")
    public class OrderFromAUserServiceImpl implements OrderFromAUserService {
        private final OrderURepository orderURepository;

        @Autowired
        public OrderFromAUserServiceImpl(OrderURepository orderURepository) {
            this.orderURepository = orderURepository;
        }

        @Override
        @CacheEvict(value = "orderUs", allEntries = true)
        @Transactional
        public OrderU create(OrderU object) {
            Objects.requireNonNull(object, "Parameter object cannot be null");
            object.setId(0);
            return orderURepository.save(object);
        }

/*
        @Override
        @Cacheable("orderfromausers")
        public OrderFromAUser read(int id) throws NotFoundException {
            //       logger.info("Get orders by id");
            return orderURepository.findById(id);
        }


 */
        @Override
        @Cacheable("orderUs")
        public OrderU read(int id) throws NotFoundException {
            return orderURepository.findById(id).orElseThrow(() -> new NotFoundException("Order with id = " + id + " not found"));
        }



        @Override
        public List<OrderU> readAll() {
            return null;
        }

        @Override
        public OrderU update(OrderU object) throws NotFoundException {
            return null;
        }

        @Override
        public void delete(int id) throws NotFoundException {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public OrderU readByName(String name) throws NotFoundException {
            return null;
        }
}
    /*

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.OrderFromAUser;
import org.java.smartrestaurant.model.Restaurant;
import org.java.smartrestaurant.repository.OrderFromAUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@CacheConfig(cacheNames = "orderfromausers")
public class OrderFromAUserServiceImpl implements OrderFromAUserService {
    private final OrderFromAUserRepository orderFromAUserRepository;
//    private static final Logger logger = LoggerFactory.getLogger(OrderFromAUserServiceImpl.class);

    @Autowired
    public OrderFromAUserServiceImpl(OrderFromAUserRepository orderFromAUserRepository) {
        this.orderFromAUserRepository = orderFromAUserRepository;
    }

    @Override
    @CacheEvict(value = "orderfromausers", allEntries = true)
    @Transactional
    public OrderFromAUser create(OrderFromAUser object) {
        Objects.requireNonNull(object, "Parameter object cannot be null");
        object.setId(0);
        return orderFromAUserRepository.save(object);
    }
/*

    @Override
    @CacheEvict(value = "orderFromAUsers", allEntries = true)
    @Transactional
    public OrderFromAUser create(OrderFromAUser orderFromAUser) {
//        logger.info("Create new order");
        Objects.requireNonNull(orderFromAUser, "Parameter order cannot be null");
        orderFromAUser.setId(0);
        return orderFromAUserRepository.save(orderFromAUser);
    }


 */
/*
    @Override
    public OrderFromAUser read(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<OrderFromAUser> readAll() {
        return null;
    }

    @Override
    public OrderFromAUser update(OrderFromAUser object) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public OrderFromAUser readByName(String name) throws NotFoundException {
        return null;
    }

/*

    @Override
    @CacheEvict(value = "orderfromausers", allEntries = true)
    @Transactional
    public void removeByDate(LocalDate date) {
//        logger.info("Remove votes by date");
        orderFromAUserRepository.removeByDate(date);
    }

    @Override
    @Cacheable("orderfromausers")
    public List<OrderFromAUser> findOrderFromAUserByDate(LocalDate date) {
//        logger.info("Find orders by date");
        return orderFromAUserRepository.findOrderFromAUsersByDate(date);
    }

    @Override
    public boolean existsOrderFromAUserByDate(LocalDate date) {
//        logger.info("Exists orders by day");
        return orderFromAUserRepository.existsOrderFromAUsersByDate(date);
    }
    @Override
    @CacheEvict(value = "orderfromausers", allEntries = true)
    @Transactional
    public void removeByDateAndUserId(LocalDate date, int userId) {
//        logger.info("Remove orderss by date and user Id");
        orderFromAUserRepository.removeByDateAndUserId(date, userId);
    }


    @Override
    @Cacheable("orderFromAUsers")
    public OrderFromAUser findOrderFromAUserByDateAndUserIdAndRestaurantId(LocalDate date, int userId, int restaurantId) {
//        logger.info("Find votes by date, userId, restaurantId");
        return orderFromAUserRepository.findOrderFromAUsersByDateAndUserIdAndRestaurantId(date, userId, restaurantId);
    }



    @Override
    @Cacheable("orderFromAUsers")
    public OrderFromAUser read(int id) throws NotFoundException {
 //       logger.info("Get orders by id");
        return orderFromAUserRepository.findById(id);
    }

    @Override
    @Cacheable("orderFromAUsers")
    public List<OrderFromAUser> readAll() {
//        logger.info("Get all votes");
        return orderFromAUserRepository.findAll();
    }

    @Override
    @CacheEvict(value = "orderFromAUsers", allEntries = true)
    @Transactional
    public OrderFromAUser update(OrderFromAUser orderFromAUser) throws NotFoundException {
        Objects.requireNonNull(orderFromAUser, "Parameter vote cannot be null");
//        logger.info("Update vote with id = {}", orderFromAUser.getId());
        if (!orderFromAUserRepository.existsById(orderFromAUser.getId())) {
            throw new NotFoundException("Vote with id = " + orderFromAUser.getId() + " not exists");
        }
        return orderFromAUserRepository.save(orderFromAUser);
    }

    @Override
    @CacheEvict(value = "orderFromAUsers", allEntries = true)
    @Transactional
    public void delete(int id) throws NotFoundException {
//       logger.info("Update vote with id = {}", id);
        orderFromAUserRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "orderFromAUsers", allEntries = true)
    @Transactional
    public void deleteAll() {
//        logger.info("Delete all orders");
        orderFromAUserRepository.deleteAll();
    }
}
*/
