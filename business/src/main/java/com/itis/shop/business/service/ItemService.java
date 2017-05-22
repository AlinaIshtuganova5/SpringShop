package com.itis.shop.business.service;

import com.itis.shop.business.service.common.Service;
import com.itis.shop.model.Item;
import com.itis.shop.model.enums.ItemGroupEnum;

import java.util.List;

/**
 * Created on 17.05.17.
 */
public interface ItemService extends Service<Item, Long> {

    public List<ItemGroupEnum> getGroups();

    List<Item> groupBy(Long groupId);

    Item addItem(Item item);

    Item edit(Long itemId, Long storageId, Long amount);
}
