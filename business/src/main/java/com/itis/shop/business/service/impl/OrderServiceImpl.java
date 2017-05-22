package com.itis.shop.business.service.impl;

import com.itis.shop.business.exceptions.NotEnoughtItemsException;
import com.itis.shop.business.exceptions.SpringShopException;
import com.itis.shop.business.repository.ItemRepository;
import com.itis.shop.business.service.OrderService;
import com.itis.shop.business.service.UserService;
import com.itis.shop.model.ItemStorage;
import com.itis.shop.model.Order;
import com.itis.shop.model.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 17.05.17.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private ItemRepository orderRepository;

    @Override
    public void buy() {
        Order order = userService.getCart();
        if (order == null)
            throw new SpringShopException();
        order.getItemOrders().forEach(itemOrder -> {
            if (itemOrder.getItem().getTotalAmount() < itemOrder.getAmount())
                throw new NotEnoughtItemsException("not enought items");
            for (ItemStorage is : itemOrder.getItem().getItemStorages()) {
                Long min = Math.min(is.getAmount(), itemOrder.getAmount());
                is.setAmount(is.getAmount() - min);
                if (itemOrder.getAmount() - min == 0)
                    break;
            }
        });
        order.setStatusEnum(StatusEnum.Done);
        userService.saveOrUpdate(userService.getCurrentUser());
    }

    @Override
    public Order saveOrUpdate(Order order) {
        return null;
    }

    @Override
    public Order find(Long aLong) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
