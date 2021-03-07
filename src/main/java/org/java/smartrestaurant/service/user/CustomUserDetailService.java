package org.java.smartrestaurant.service.user;

import org.java.smartrestaurant.config.security.AuthorizedUser;
import org.java.smartrestaurant.model.Role;
import org.java.smartrestaurant.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);
    private final UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.readByName(name);
        logger.info("loadByUserName: " + user);
        if (user == null) {
            throw new UsernameNotFoundException("User with name = " + name + " is not found");
        }
        return new AuthorizedUser(user);
    }

    private Collection<GrantedAuthority> getGrandedAuthorities(User user) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (isListContainString(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()), "ADMIN")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (isListContainString(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()), "USER")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return grantedAuthorities;
    }

    private boolean isListContainString(List<String> list, String value) {
        return !list.stream().filter(el -> el.equals(value))
                .collect(Collectors.toList()).isEmpty();
    }
}
