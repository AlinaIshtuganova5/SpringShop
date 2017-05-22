package com.itis.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itis.shop.dto.common.AbstractEntityDto;
import com.itis.shop.model.Storage;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 18.05.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageDto extends AbstractEntityDto {

    private String address;
    private String city;
    private Set<ItemStorageDto> itemStorages = new HashSet<>();

    public StorageDto() {
    }

    public StorageDto(Storage storage) {
        super(storage);
        this.address = storage.getAddress();
        this.city = storage.getCity();
        this.itemStorages = storage.getItemStorages().stream().map(ItemStorageDto::new).collect(Collectors.toSet());
    }

    public Set<ItemStorageDto> getItemStorages() {
        return itemStorages;
    }

    public void setItemStorages(Set<ItemStorageDto> itemStorages) {
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
