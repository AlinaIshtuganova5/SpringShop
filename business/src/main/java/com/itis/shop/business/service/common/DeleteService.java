package com.itis.shop.business.service.common;

/**
 * Created on 17.05.17.
 */
public interface DeleteService<T, ID> extends Service<T, ID> {

    void delete(T t);

    void deleteById(ID id);
}
