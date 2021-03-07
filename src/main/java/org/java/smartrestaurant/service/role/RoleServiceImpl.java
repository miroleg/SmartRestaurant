package org.java.smartrestaurant.service.role;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Role;
import org.java.smartrestaurant.repository.RoleRepository;
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
@CacheConfig(cacheNames = "roles")
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = "roles", allEntries = true)
    @Transactional
    public Role create(Role role) {
        Objects.requireNonNull(role, "Parameter role cannot be null");
        role.setId(0);
        return repository.save(role);
    }

    @Override
    public Role read(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Role with id = " + id + " not found"));
    }

    @Override
    @Cacheable("roles")
    public List<Role> readAll() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(value = "roles", allEntries = true)
    @Transactional
    public Role update(Role role) throws NotFoundException {
        Objects.requireNonNull(role, "Parameter role cannot be null");
        if (!repository.existsById(role.getId())) {
            throw new NotFoundException("Role with id = " + role.getId() + " not exists");
        }
        return repository.save(role);
    }

    @Override
    @CacheEvict(value = "roles", allEntries = true)
    @Transactional
    public void delete(int id) throws NotFoundException {
        if (repository.removeById(id) == 0) {
            throw new NotFoundException("Role with id = " + id + " not found");
        }
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Role readByName(String name) throws NotFoundException {
        return Optional.ofNullable(repository.findByName(name))
                .orElseThrow(() -> new NotFoundException("Role with name = " + name + " not found"));
    }

}
