package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.model.Role;
import org.java.smartrestaurant.service.role.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RoleAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleAdminController {
    private static final Logger logger = LoggerFactory.getLogger(RoleAdminController.class);
    private final RoleService roleService;
    static final String REST_URL = "/admin/roles";

    @Autowired
    public RoleAdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Role> getAll() {
        logger.info("Retrieve all roles");
        return roleService.readAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        logger.info("Put new role with info {}", role);
        Role created = roleService.create(role);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Role update(@Valid @RequestBody Role role) {
        logger.info("Update role {} with info {}", role.getId(), role);
        return roleService.update(role);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.FOUND)
    public Role read(@PathVariable int id) {
        logger.info("Get role with if = {}", id);
        return roleService.read(id);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Role read(@RequestParam(value = "name", required = true) String name) {
        logger.info("Get role by name = {}", name);
        return roleService.readByName(name);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAll() {
        logger.info("Delete all roles");
        roleService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        logger.info("Delete role with id = {}", id);
        roleService.delete(id);
    }

}
