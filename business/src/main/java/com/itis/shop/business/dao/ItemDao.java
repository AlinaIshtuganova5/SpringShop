package com.itis.shop.business.dao;

import com.itis.shop.model.Item;

import java.util.List;

/**
 * Created on 19.05.17.
 */
public interface ItemDao {

    List<Item> search(String seach) throws InterruptedException;
}
