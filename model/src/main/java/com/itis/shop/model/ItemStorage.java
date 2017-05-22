package com.itis.shop.model;

import com.itis.shop.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created on 16.05.17.
 */
@DynamicUpdate
@DynamicInsert
@Table(name = "item_storage")
@Entity
public class ItemStorage extends AbstractEntity {

    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;
    @JoinColumn(name = "storage_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Storage storage;
    @Column(name = "amount")
    private Long amount;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}

