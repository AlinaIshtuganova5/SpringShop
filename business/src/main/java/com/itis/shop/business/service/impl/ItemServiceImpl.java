package com.itis.shop.business.service.impl;

import com.itis.shop.business.exceptions.SpringShopException;
import com.itis.shop.business.repository.ItemRepository;
import com.itis.shop.business.repository.StorageRepository;
import com.itis.shop.business.service.ItemService;
import com.itis.shop.model.Item;
import com.itis.shop.model.ItemStorage;
import com.itis.shop.model.enums.ItemGroupEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 17.05.17.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item edit(Long itemId, Long storageId, Long amount) {
        Item item = itemRepository.findOne(itemId);
        if (item == null)
            throw new SpringShopException();
        ItemStorage itemStorage = item.getItemStorages().stream()
                .filter(is -> is.getStorage().getId().equals(storageId))
                .findAny().orElse(null);
        if (itemStorage == null) {
            itemStorage = new ItemStorage();
            itemStorage.setItem(item);
            itemStorage.setStorage(storageRepository.findOne(storageId));
            itemStorage.setAmount(amount);
            item.getItemStorages().add(itemStorage);
        } else {
            itemStorage.setAmount( amount);
        }
        return saveOrUpdate(item);
    }

    @Override
    public Item addItem(Item item) {
        return saveOrUpdate(item);
    }

    @Override
    public List<ItemGroupEnum> getGroups() {
        List<ItemGroupEnum> group = new ArrayList<>();
        Arrays.stream(ItemGroupEnum.values()).forEach(itemGroupEnum -> group.add(itemGroupEnum));
        return group;
    }

    @Override
    public List<Item> groupBy(Long groupId) {
        ItemGroupEnum itemGroupEnum = ItemGroupEnum.getById(groupId);
        if (itemGroupEnum == null)
            throw new SpringShopException();
        return itemRepository.findByGroupEquals(itemGroupEnum);
    }

    @Override
    public Item saveOrUpdate(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item find(Long id) {
        Item item = itemRepository.findOne(id);
        if (item == null)
            throw new SpringShopException();
        return item;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
