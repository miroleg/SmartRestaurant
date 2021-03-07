package org.java.smartrestaurant.service.restaurant;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Restaurant;
import org.java.smartrestaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "restaurants")
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    public Restaurant create(Restaurant object) {
        Objects.requireNonNull(object, "Parameter object cannot be null");
        object.setId(0);
        return restaurantRepository.save(object);
    }

    @Override
    @Cacheable("restaurants")
    public Restaurant read(int id) throws NotFoundException {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant with id = " + id + " not found"));
    }

    @Override
    @Cacheable("restaurants")
    public List<Restaurant> readAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant readByName(String name) throws NotFoundException {
        return Optional.ofNullable(restaurantRepository.findByName(name))
                .orElseThrow(() -> new NotFoundException("Restaurant with name = " + name + " not found"));
    }

    @Override
    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    public Restaurant update(Restaurant object) throws NotFoundException {
        Objects.requireNonNull(object, "Parameter object cannot be null");
        if (!restaurantRepository.existsById(object.getId())) {
            throw new NotFoundException("User with id = " + object.getId() + " not exists");
        }
        return restaurantRepository.save(object);
    }

    @Override
    @CacheEvict(value = {"restaurants", "dishes"}, allEntries = true)
    @Transactional
    public void delete(int id) throws NotFoundException {
        if (restaurantRepository.removeById(id) == 0) {
            throw new NotFoundException("Error during deleting restaurant with id = " + id + " not found");
        }
    }

    @Override
    @CacheEvict(value = {"restaurants", "dishes"}, allEntries = true)
    @Transactional
    public void deleteAll() {
        restaurantRepository.deleteAll();
    }
}
