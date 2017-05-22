package com.itis.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itis.shop.dto.common.AbstractEntityDto;
import com.itis.shop.model.Order;
import com.itis.shop.model.enums.StatusEnum;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 18.05.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto extends AbstractEntityDto {

    private Long userId;
    private Set<ItemOrderDto> itemOrders = new HashSet<>();
    private StatusEnum statusEnum;

    public OrderDto() {
    }

    public OrderDto(Order order) {
        super(order);
        this.userId = order.getUser() == null ? null : order.getUser().getId();
        this.itemOrders = order.getItemOrders().stream().map(ItemOrderDto::new).collect(Collectors.toSet());
        this.statusEnum = order.getStatusEnum();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<ItemOrderDto> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(Set<ItemOrderDto> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
