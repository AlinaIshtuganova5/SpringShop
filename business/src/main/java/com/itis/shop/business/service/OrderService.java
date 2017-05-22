package com.itis.shop.business.service;

import com.itis.shop.business.service.common.DeleteService;
import com.itis.shop.model.Order;

/**
 * Created on 17.05.17.
 */
public interface OrderService extends DeleteService<Order, Long> {

    void buy();
}
