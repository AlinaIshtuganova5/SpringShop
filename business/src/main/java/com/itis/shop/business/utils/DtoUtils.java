package com.itis.shop.business.utils;

import com.itis.shop.business.repository.RoleRepository;
import com.itis.shop.business.repository.UserRepository;
import com.itis.shop.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 18.05.17.
 */
@Component
public class DtoUtils {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

}
