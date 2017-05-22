package com.itis.shop.model.enums;

/**
 * Created on 16.05.17.
 */
public enum StatusEnum {

    Setting("Набирается"),
    Forming("Формируется"),
    Sending("Шлется"),
    Done("Выполнен");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
