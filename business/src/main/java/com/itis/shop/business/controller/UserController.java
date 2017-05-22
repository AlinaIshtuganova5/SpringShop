package com.itis.shop.business.controller;

import com.itis.shop.business.exceptions.SpringShopException;
import com.itis.shop.business.service.UserService;
import com.itis.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 17.05.17.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String me(Model model) {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new SpringShopException();
        }
        model.addAttribute("user", user);
        return "user";
    }


}
