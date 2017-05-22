package com.itis.shop.business.exceptions;

import com.itis.shop.business.service.ItemService;
import com.itis.shop.business.service.UserService;
import com.itis.shop.model.enums.ItemGroupEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created on 18.05.17.
 */
@ControllerAdvice
public class ExceptionHandler {

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Throwable.class})
    public ModelAndView handleAnyException(Throwable ex, HttpServletRequest request) {
        return new ModelAndView("error_page", "message", "Oops, something went wrong((");
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Throwable.class})
//    public String exception(Throwable e, Model model) {
//        model.addAttribute("message", e.getMessage());
//        return "error_page";
//    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {SpringShopException.class})
    public String exception(SpringShopException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error_page";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotEnoughtItemsException.class})
    public String exception(NotEnoughtItemsException e, Model model) {
        model.addAttribute("message", e.getMessage());
        if (e.getView().equals("/user")) {
            model.addAttribute("user", userService.getCurrentUser());
            e.setView("user");
        } else if (e.getView().equals("/")) {
            e.setView("redirect:/");
        } else if (e.getView().equals("/items/")) {
            model.addAttribute("item", itemService.find(e.getItemId()));
            e.setView("item");
        } else if (e.getView().equals("/cart")) {
            model.addAttribute("cart", userService.getCart());
            e.setView("cart");
        }
        model.addAttribute("itemId", e.getItemId());
        return e.getView();
    }
}
