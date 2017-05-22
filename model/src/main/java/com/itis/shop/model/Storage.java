package com.itis.shop.model;

import com.itis.shop.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 16.05.17.
 */
@Entity
@Table(name = "storage")
@DynamicInsert
@DynamicUpdate
public class Storage extends AbstractEntity {

    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "storage")
    private Set<ItemStorage> itemStorages = new HashSet<>();

    public Set<ItemStorage> getItemStorages() {
        return itemStorages;
    }

    public void setItemStorages(Set<ItemStorage> itemStorages) {
        this.itemStorages = itemStorages;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
