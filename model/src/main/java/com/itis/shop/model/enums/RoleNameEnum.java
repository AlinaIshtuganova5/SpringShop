package com.itis.shop.model.enums;

/**
 * Created on 17.05.17.
 */
public enum RoleNameEnum {
    Admin("ROLE_ADMIN"),
    User("ROLE_USER");

    private String name;

    RoleNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
