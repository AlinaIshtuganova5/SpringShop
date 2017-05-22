package com.itis.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itis.shop.dto.common.AbstractEntityDto;
import com.itis.shop.model.ItemOrder;

/**
 * Created on 18.05.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemOrderDto extends AbstractEntityDto {

    private ItemDto item;
    private Long orderId;
    private Long amount;

    public ItemOrderDto() {
    }

    public ItemOrderDto(ItemOrder itemOrder) {
        super(itemOrder);
        this.item = itemOrder.getItem() == null ? null : new ItemDto(itemOrder.getItem());
        this.orderId = itemOrder.getOrder() == null ? null : itemOrder.getOrder().getId();
        this.amount = itemOrder.getAmount();
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
