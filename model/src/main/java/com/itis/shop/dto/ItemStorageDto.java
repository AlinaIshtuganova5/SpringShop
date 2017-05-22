package com.itis.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itis.shop.dto.common.AbstractEntityDto;
import com.itis.shop.model.ItemStorage;

/**
 * Created on 18.05.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemStorageDto extends AbstractEntityDto {

    private ItemDto item;
    private Long storageId;
    private Long amount;

    public ItemStorageDto() {
    }

    public ItemStorageDto(ItemStorage itemStorage) {
        super(itemStorage);
        this.item = itemStorage.getItem() == null ? null : new ItemDto(itemStorage.getItem());
        this.storageId = itemStorage.getStorage() == null ? null : itemStorage.getStorage().getId();
        this.amount = itemStorage.getAmount();
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
