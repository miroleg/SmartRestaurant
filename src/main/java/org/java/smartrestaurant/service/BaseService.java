package org.java.smartrestaurant.service;

import org.java.smartrestaurant.exception.NotFoundException;

import java.util.List;

public interface BaseService<T> {
    T create(T object);
    T read(int id) throws NotFoundException;
    List<T> readAll();
    T update(T object) throws NotFoundException;
    void delete(int id) throws NotFoundException;
    void deleteAll();


}
