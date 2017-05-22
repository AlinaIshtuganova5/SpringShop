package com.itis.shop.model.enums;

import java.util.Arrays;

/**
 * Created on 19.05.17.
 */
public enum ItemGroupEnum {
    Food(1L),
    Toys(2L),
    Transport(3L),
    Books(4L);
    private Long id;

    ItemGroupEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static ItemGroupEnum getById(Long id) {
        return Arrays.stream(values())
                .filter(itemGroupEnum -> itemGroupEnum.getId().equals(id)).findFirst().orElse(null);
    }
}
