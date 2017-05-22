package com.itis.shop.business.service.impl;

import com.itis.shop.business.exceptions.NotEnoughtItemsException;
import com.itis.shop.business.exceptions.SpringShopException;
import com.itis.shop.business.repository.RoleRepository;
import com.itis.shop.business.repository.UserRepository;
import com.itis.shop.business.service.ItemService;
import com.itis.shop.business.service.UserService;
import com.itis.shop.business.utils.DtoUtils;
import com.itis.shop.model.Item;
import com.itis.shop.model.ItemOrder;
import com.itis.shop.model.Order;
import com.itis.shop.model.User;
import com.itis.shop.model.enums.RoleNameEnum;
import com.itis.shop.model.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created on 17.05.17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DtoUtils dtoUtils;
    @Autowired
    private ItemService itemService;

    @Override
    public Order getCart() {
        return getCurrentUser().getOrders().stream()
                .filter(order -> order.getStatusEnum().equals(StatusEnum.Setting))
                .findFirst().orElse(null);
    }

    @Override
    public void setEnabled(Long userId) {
        User user = find(userId);
        if (user.getEmail().equals("admin"))
            return;
        if (user.getEnabled().equals(true))
            user.setEnabled(false);
        else user.setEnabled(true);
    }

    @Override
    public void addItem(Long itemId, String view) {
        Item item = itemService.find(itemId);
        if (item == null)
            throw new SpringShopException();
        User user = getCurrentUser();
        Order order = user.getOrders().stream()
                .filter(o -> o.getStatusEnum().equals(StatusEnum.Setting)).findFirst().orElse(null);
        if (order == null) {
            order = new Order();
            order.setUser(user);
            user.getOrders().add(order);
            order.setStatusEnum(StatusEnum.Setting);
        }
        ItemOrder itemOrder = order.getItemOrders().stream()
                .filter(io -> io.getItem().equals(item)).findFirst().orElse(null);
        if (itemOrder == null) {
            itemOrder = new ItemOrder();
            itemOrder.setOrder(order);
            itemOrder.setItem(item);
            itemOrder.setOrder(order);
            itemOrder.setAmount(0L);
            order.getItemOrders().add(itemOrder);
        }
        if (itemOrder.getAmount() + 1 > item.getTotalAmount())
            throw new NotEnoughtItemsException(view, itemId);
        itemOrder.setAmount(itemOrder.getAmount() + 1);
        saveOrUpdate(user);
    }

    @Override
    public Boolean confirmAccount(String token, String email) {
        User user = findUserbyEmail(email);
        if (user != null && user.getToken() != null && user.getToken().equals(token)) {
            user.setEnabled(true);
            user.setToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username == null ? null : findUserbyEmail(username);

    }

    @Override
    public User signUp(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.getRoles().add(roleRepository.findByName(RoleNameEnum.User.getName()));
        user.setToken(UUID.randomUUID().toString());
        return saveOrUpdate(user);
    }

    @Override
    public User findUserbyEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = find(userId);
        if (user == null)
            throw new SpringShopException();
        if (user.getEmail().equals("admin"))
            return;
        delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User find(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserbyEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(String.format("Email '%s' not found", email));
        if (!user.getEnabled())
            throw new DisabledException("This user is disabled");
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }


}
