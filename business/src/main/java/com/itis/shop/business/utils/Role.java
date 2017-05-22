package com.itis.shop.business.utils;

import com.itis.shop.model.enums.RoleNameEnum;
import org.springframework.stereotype.Component;

/**
 * Created on 19.05.17.
 */
@Component
public class Role {

    public static final String Admin = RoleNameEnum.Admin.getName();
    public static final String User = RoleNameEnum.User.getName();

}
