package org.java.smartrestaurant.web.controller;

import org.java.smartrestaurant.dto.UserDto;
import org.java.smartrestaurant.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.java.smartrestaurant.util.entity.UserUtil.createDtoFrom;
import static org.java.smartrestaurant.util.entity.UserUtil.createUserFromDto;

@RestController
@RequestMapping(value = RegisterController.REST_URL)
public class RegisterController {
    static final String REST_URL = "/public/register";
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) {
        logger.info("Register new user with info {}", userDto);
        UserDto created = createDtoFrom(userService.create(createUserFromDto(userDto)));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/public/register" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
