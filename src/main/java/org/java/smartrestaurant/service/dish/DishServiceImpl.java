package org.java.smartrestaurant.service.dish;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Dish;
import org.java.smartrestaurant.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "dishes")
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    @CacheEvict(value = "dishes", allEntries = true)
    @Transactional
    public Dish create(Dish dish) {
        Objects.requireNonNull(dish, "Parameter dish cannot be null");
        dish.setId(0);
        return dishRepository.save(dish);
    }

    @Override
    public Dish read(int id) throws NotFoundException {
        return dishRepository.findById(id).orElseThrow(() -> new NotFoundException("Dish with id = " + id + " not found"));
    }

    @Override
    @Cacheable("dishes")
    public List<Dish> readAll() {
        return dishRepository.findAll();
    }

    @Override
    public List<Dish> readByNameLike(String name) {
        return dishRepository.findByNameIgnoreCaseContaining(name);
    }
/*    @Override
    public List<Dish> readPaginated(int page, int size) {
        return dishRepository.findAll(new PageRequest(page, size)).get().collect(Collectors.toList());
    }

 */

    @Override
    @CacheEvict(value = "dishes", allEntries = true)
    @Transactional
    public Dish update(Dish dish) throws NotFoundException {
        Objects.requireNonNull(dish, "Parameter dish cannot be null");
        return dishRepository.save(dish);
    }

    @Override
    @CacheEvict(value = "dishes", allEntries = true)
    @Transactional
    public void delete(int id) throws NotFoundException {
        if (dishRepository.removeById(id) == 0) {
            throw new NotFoundException("Dish with id = " + id + " not found");
        }
    }

    @Override
    @CacheEvict(value = "dishes", allEntries = true)
    @Transactional
    public void deleteAll() {
        dishRepository.deleteAll();
    }

    @Override
    @CacheEvict(value = {"dishes", "restaurants"}, allEntries = true)
    @Transactional
    public void deleteAllByRestaurantId(int restaurantId) {
        dishRepository.removeByRestaurantId(restaurantId);
    }

    public List<Dish> getDishByRestaurantId(int id) {
        return dishRepository.getDishByRestaurantId(id);
    }
}
