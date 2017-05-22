package com.itis.shop.business.repository;

import com.itis.shop.model.Item;
import com.itis.shop.model.enums.ItemGroupEnum;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 17.05.17.
 */
@Repository
@Lazy
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    List<Item> findByGroupEquals(ItemGroupEnum group);
}
