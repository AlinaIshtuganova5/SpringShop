package com.itis.shop.model;

import com.itis.shop.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created on 16.05.17.
 */
@DynamicInsert
@DynamicUpdate
@Table(name = "item_order")
@Entity
public class ItemOrder extends AbstractEntity {

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
    @JoinColumn(name = "orders_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
    @Column(name = "amount")
    private Long amount;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
