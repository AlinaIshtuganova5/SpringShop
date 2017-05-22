package com.itis.shop.model;

import com.itis.shop.model.common.AbstractEntity;
import com.itis.shop.model.enums.ItemGroupEnum;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 16.05.17.
 */
@DynamicInsert
@DynamicUpdate
@Table(name = "item")
@Entity
@Indexed
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "English"),
                        @Parameter(name = "language", value = "Russian")

                })
        })
public class Item extends AbstractEntity {

    @Column(name = "name")
    @Field
    @Analyzer(definition = "customanalyzer")
    @NotNull(message = "Name can not be empty")
    private String name;
    @Column(name = "description")
    @Field
    @Analyzer(definition = "customanalyzer")
    private String description;
    @Column(name = "cost")
    @Field
    private Double cost;
    @Column(name = "item_group")
    @Enumerated(EnumType.STRING)
    private ItemGroupEnum group;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
    private Set<ItemStorage> itemStorages = new HashSet<>();
    @Transient
    private Double totalAmount;

    public ItemGroupEnum getGroup() {
        return group;
    }

    public void setGroup(ItemGroupEnum group) {
        this.group = group;
    }

    public Double getTotalAmount() {
        totalAmount = 0D;
        itemStorages.forEach(itemStorage -> totalAmount += itemStorage.getAmount());
        return totalAmount;
    }

    public Set<ItemStorage> getItemStorages() {
        return itemStorages;
    }

    public void setItemStorages(Set<ItemStorage> itemStorages) {
        this.itemStorages = itemStorages;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
