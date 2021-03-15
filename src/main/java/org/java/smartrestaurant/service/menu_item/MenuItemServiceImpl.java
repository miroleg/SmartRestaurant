package org.java.smartrestaurant.service.menu_item;

import org.java.smartrestaurant.dto.DishForUserDto;
import org.java.smartrestaurant.dto.MenuForUserDto;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.MenuItem;
import org.java.smartrestaurant.model.Restaurant;
import org.java.smartrestaurant.repository.MenuItemRepository;
import org.java.smartrestaurant.util.entity.RestaurantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "menuItems")
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }


    @Override
    @Cacheable("menuItems")
    public List<MenuItem> readByDateAndRestaurant(int restaurantId, LocalDate dateParam) {
        return menuItemRepository.findByDateAndRestaurant(restaurantId, dateParam);

    }

    @Override
    @CacheEvict(value = "menuItems", allEntries = true)
    @Transactional
    public void deleteByDateAndRestaurant(int restaurantId, LocalDate dateParam) {
        menuItemRepository.removeByDateAndRestaurant(restaurantId, dateParam);
    }

    @Override
    @Cacheable("menuItems")
    public List<MenuItem> readByDate(LocalDate dateParam) {
        return menuItemRepository.findByDate(dateParam);
    }

    @Override
    @CacheEvict(value = "menuItems", allEntries = true)
    @Transactional
    public MenuItem create(MenuItem object) {
        Objects.requireNonNull(object, "Parameter object cannot be null");
        if (!object.getDish().getRestaurant().getId().equals(object.getRestaurant().getId())) {
            throw new NotFoundException("Not found dish with id = " + object.getDish().getId() + " for restaurant with id = " + object.getRestaurant().getId());
        }
        object.setId(0);
        return menuItemRepository.save(object);
    }

    @Override
    @Cacheable("menuItems")
    public MenuItem read(int id) throws NotFoundException {
        return menuItemRepository.findById(id).orElseThrow(() -> new NotFoundException("MenuItem with id = " + id + " not found"));
    }

    @Override
    @Cacheable("menuItems")
    public List<MenuItem> readAll() {
        return menuItemRepository.findAll();
    }

    @Override
    @CacheEvict(value = "menuItems", allEntries = true)
    @Transactional
    public MenuItem update(MenuItem object) throws NotFoundException {
        Objects.requireNonNull(object, "Parameter object cannot be null");
        if (!menuItemRepository.existsById(object.getId())) {
            throw new NotFoundException("MenuItem with id = " + object.getId() + " not exists");
        }
        if (!object.getDish().getRestaurant().getId().equals(object.getRestaurant().getId())) {
            throw new NotFoundException("Not found dish with id = " + object.getDish().getId() + " for restaurant with id = " + object.getRestaurant().getId());
        }
        return menuItemRepository.save(object);
    }

    @Override
    @CacheEvict(value = "menuItems", allEntries = true)
    @Transactional
    public void delete(int id) throws NotFoundException {
        if (menuItemRepository.removeById(id) == 0) {
            throw new NotFoundException("MenuItem with id = " + id + " not found");
        }
    }

    @Override
    @CacheEvict(value = "menuItems", allEntries = true)
    @Transactional
    public void deleteAll() {
        menuItemRepository.deleteAll();
    }

    public List<MenuForUserDto> getMenuForDate(LocalDate date) {
        List<MenuItem> menuItems = readByDate(date);
        if (menuItems.isEmpty()) {
            throw new NotFoundException("No menu found for this date.");
        }
        Map<Restaurant, List<MenuItem>> collect = menuItems.stream()
                .collect(Collectors.groupingBy(MenuItem::getRestaurant));
        List<MenuForUserDto> list = new ArrayList<>();
        collect.entrySet().forEach(el -> {
            List<DishForUserDto> collect1 = el.getValue().stream().map(el1 -> new DishForUserDto(el1.getDish().getId(), el1.getDish().getName(),
                    el1.getDish().getDescription(), el1.getPrice())).sorted(Comparator.comparing(DishForUserDto::getId)).collect(Collectors.toList());
            list.add(new MenuForUserDto(date,
                    RestaurantUtil.createDtoFrom(el.getKey()), collect1));
        });

        return list.stream().sorted(Comparator.comparing(el -> el.getRestaurant().getId())).collect(Collectors.toList());

    }


    @Override
    public void deleteAllByDate(LocalDate date) {
        menuItemRepository.deleteAllByDatei(date);
    }
}
