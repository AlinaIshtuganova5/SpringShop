package com.itis.shop.business.service.common;

import java.util.List;

/**
 * Created on 17.05.17.
 */
public interface Service<T, ID> {

    T saveOrUpdate(T t);

    T find(ID id);

    List<T> findAll();
}
