package com.itis.shop.model;

import com.itis.shop.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created on 16.05.17.
 */
@Entity
@Table(name = "role")
@DynamicInsert
@DynamicUpdate
public class Role extends AbstractEntity {

    @Column(nullable = false, unique = true, insertable = false, name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
