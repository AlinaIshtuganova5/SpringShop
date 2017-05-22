package com.itis.shop.model.common;

import java.io.Serializable;

/**
 * Created on 16.05.17.
 */
public interface Entity<T> extends Serializable{

    T getId();

    void setId(T id);

}
