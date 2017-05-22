package com.itis.shop.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itis.shop.model.common.AbstractEntity;

/**
 * Created on 18.05.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractEntityDto {

    private Long id;

    public AbstractEntityDto() {
    }

    public AbstractEntityDto(AbstractEntity abstractEntity) {
        this.id = abstractEntity.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
