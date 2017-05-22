package com.itis.shop.business.service;

import com.itis.shop.business.service.common.DeleteService;
import com.itis.shop.dto.UserDto;
import com.itis.shop.model.Order;
import com.itis.shop.model.User;

/**
 * Created on 17.05.17.
 */
public interface UserService extends DeleteService<User, Long> {

    User getCurrentUser();

    User findUserbyEmail(String email);

    User signUp(User user);

    Boolean confirmAccount(String token, String email);

    void addItem(Long itemId, String view);

    void setEnabled(Long userId);

    void deleteUser(Long userId);

    Order getCart();
}
