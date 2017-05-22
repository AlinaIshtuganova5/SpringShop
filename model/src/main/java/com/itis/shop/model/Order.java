package com.itis.shop.model;

import com.itis.shop.model.common.AbstractEntity;
import com.itis.shop.model.enums.StatusEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 16.05.17.
 */
@DynamicInsert
@DynamicUpdate
@Table(name = "orders")
@Entity
public class Order extends AbstractEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ItemOrder> itemOrders = new HashSet<>();
    @JoinColumn(name = "users_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    public Set<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(Set<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
