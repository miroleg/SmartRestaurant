package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.dto.UserDto;
import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Role;
import org.java.smartrestaurant.model.User;
import org.java.smartrestaurant.service.role.RoleService;
import org.java.smartrestaurant.service.user.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.java.smartrestaurant.util.entity.UserUtil.*;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = UserAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAdminController {
    private static final Logger logger = getLogger(UserAdminController.class);
    static final String REST_URL = "/admin/users";
    private UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> getAll() {
        logger.info("Retrieve all users");
        return createDtoListFromUserList(userService.readAll());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.FOUND)
    public UserDto read(@PathVariable int id) {
        logger.info("Get user with id = {}", id);
        return createDtoFrom(userService.read(id));
    }

    @GetMapping(value = "/sort")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> getSortingBy(
            @RequestParam(value = "order", required = true) String order) {
        logger.info("Get users sorting by {}", order);
        List<User> userList;
        switch (order) {
/*            case "name":
                userList = userService.readAllOrderByName();
                break;
            case "email":
                userList = userService.readAllOrderByEmail();
                break;
 */
            default:
                userList = userService.readAll();
                break;
        }
        return createDtoListFromUserList(userList);
    }

    @GetMapping(value = "/filter")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> getByFilterNameLike(@RequestParam(value = "name", required = true) String name) {
        logger.info("Get users filtering by name {}", name);
        var userList = userService.readByNameLike(name);
        return userList != null ? createDtoListFromUserList(userList) : Collections.emptyList();
    }
/*
    @GetMapping(value = "/page")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserDto> findPaginated(
            @RequestParam(value = "offset", required = true) int offset,
            @RequestParam(value = "size", required = true) int size) {
        logger.info("Get Page with users");
        return createDtoListFromUserList(userService.readPaginated(offset, size));
    }

 */

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDto enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        logger.info("Set enable = {} to user with id = {}", enabled, id);
        return createDtoFrom(userService.setEnabled(id, enabled));
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserDto update(@Valid @RequestBody UserDto userDto, @PathVariable("id") int id) {
        logger.info("Update user {} with info {}", userDto.getId(), userDto);
        if (id != userDto.getId()) {
            throw new NotFoundException("PathVariable = " + id + ", but in RequestBody found id = " + userDto.getId() + ". id must be the same");
        }
        User user = userService.read(userDto.getId());
        return createDtoFrom(userService.update(updateUserFromDto(user, userDto)));
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) {
        logger.info("Put new user with info {}", userDto);
        UserDto created = createDtoFrom(userService.create(createUserFromDto(userDto)));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/{id}/roles")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Role> getRolesById(@PathVariable("id") int id) {
        logger.info("Get roles for user with id = {}", id);
        return userService.read(id).getRoles();
    }

    @PostMapping(value = "/{id}/roles/{roleId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<List<Role>> addRole(@PathVariable("id") int id, @PathVariable("roleId") int roleId) {
        logger.info("Add role with id = {} for user with id = {}", roleId, id);
        Role role = roleService.read(roleId);
        User user = userService.read(id);
        user.addRole(role);
        userService.update(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}/roles")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(user.getRoles());
    }

    @DeleteMapping(value = "/{id}/roles/{roleId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable("id") int id, @PathVariable("roleId") int roleId) {
        logger.info("Delete role with id = {} for user with id = {}", roleId, id);
        Role role = roleService.read(roleId);
        User user = userService.read(id);
        user.deleteRole(role);
        userService.update(user);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAll() {
        logger.info("Delete all users");
        userService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        logger.info("Delete users with id = {}", id);
        userService.delete(id);
    }


}

