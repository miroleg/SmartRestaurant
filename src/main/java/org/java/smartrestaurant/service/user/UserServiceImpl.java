package org.java.smartrestaurant.service.user;

import org.java.smartrestaurant.exception.NotFoundException;
import org.java.smartrestaurant.model.Role;
import org.java.smartrestaurant.model.User;
import org.java.smartrestaurant.repository.RoleRepository;
import org.java.smartrestaurant.repository.UserRepository;
import org.java.smartrestaurant.util.entity.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@EnableWebMvc
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public User create(User user) {
        Objects.requireNonNull(user, "Parameter user cannot be null");
        user.setId(0);
        List<Role> roleList = new ArrayList<Role>() {{
            add(roleRepository.findByName("USER"));
        }};
        user.setRoles(roleList);
        return userRepository.save(UserUtil.prepareToSave(user, new BCryptPasswordEncoder()));
    }

    @Override
    public User read(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id = " + id + " not found"));
    }

    @Override
    @Cacheable("users")
    public List<User> readAll() {
        return userRepository.findAll();
    }
/*
    @Override
    @Cacheable("users")
    public List<User> readAllOrderByName() {
        return userRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }


 */
    @Override
    public List<User> readByNameLike(String name) {
        return userRepository.findByNameIgnoreCaseContaining(name);
    }


/*
    @Override
    @Cacheable("users")
    public List<User> readAllOrderByEmail() {
        return userRepository.findAll(new Sort(Sort.Direction.ASC, "email"));
    }


    @Override
    public List<User> readPaginated(int page, int size) {
        Page<User> all = userRepository.findAll(new PageRequest(page, size));
        return all.get().collect(Collectors.toList());
    }

 */


    @Override
    public User readByName(String name) throws NotFoundException {
        return Optional.ofNullable(userRepository.findByName(name))
                .orElseThrow(() -> new NotFoundException("User with name = " + name + " not found"));
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public User update(User user) throws NotFoundException {
        Objects.requireNonNull(user, "Parameter user cannot be null");
        if (!userRepository.existsById(user.getId())) {
            throw new NotFoundException("User with id = " + user.getId() + " not exists");
        }
        User oldUser = userRepository.getOne(user.getId());
        String password = oldUser.getPassword();
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void delete(int id) throws NotFoundException {
        if (userRepository.removeById(id) == 0) {
            throw new NotFoundException("User with id = " + id + " not found");
        }
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public User setEnabled(int id, boolean enabled) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id = " + id + " not found"));
        user.setEnabled(enabled);
        update(user);
        return user;
    }


    @Override
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void updatePassword(User user, String oldPassword, String newPassword) {
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }


}
