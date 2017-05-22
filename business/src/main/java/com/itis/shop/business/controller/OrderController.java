package com.itis.shop.business.controller;

import com.itis.shop.business.service.OrderService;
import com.itis.shop.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 19.05.17.
 */
@Controller
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole(@role.User, @role.Admin)")
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getCard(Model model) {
        model.addAttribute("cart", userService.getCart());
        return "cart";
    }

    @PreAuthorize("hasRole(@role.User)")
    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buy(Model model){
        orderService.buy();
        return "redirect:/user";
    }
}
