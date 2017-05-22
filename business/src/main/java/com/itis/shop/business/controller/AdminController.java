package com.itis.shop.business.controller;

import com.itis.shop.business.exceptions.SpringShopException;
import com.itis.shop.business.service.ItemService;
import com.itis.shop.business.service.OrderService;
import com.itis.shop.business.service.StorageService;
import com.itis.shop.business.service.UserService;
import com.itis.shop.model.Item;
import com.itis.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created on 17.05.17.
 */
@Controller
@PreAuthorize("hasRole(@role.Admin)")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/admin/users/enable/{userId}", method = RequestMethod.POST)
    public String setEnabled(@PathVariable Long userId, Model model) {
        userService.setEnabled(userId);
        return "redirect:/admin/users/" + userId;
    }

    @RequestMapping(value = "/admin/users/delete/{userId}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/users/{userId}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable Long userId) {
        User user = userService.find(userId);
        if (user == null) throw new SpringShopException();
        model.addAttribute("user", user);
        return "user";
    }

    @RequestMapping(value = "/admin/item", method = RequestMethod.GET)
    public String getItemAddingPage(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("group", itemService.getGroups());
        return "add_item";
    }

    @RequestMapping(value = "/admin/item", method = RequestMethod.POST)
    public String addItem(@ModelAttribute("item") @Valid Item item, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "add_item";
        }
        item = itemService.addItem(item);
        if (item.getId() == null)
            throw new SpringShopException();
        return "redirect:/items/" + item.getId();
    }

    @RequestMapping(value = "/admin/edit/item/{itemId}", method = RequestMethod.POST)
    public String editItem(Model model, @RequestParam(name = "storage_id") Long storageId,
                           @RequestParam(name = "amount") Long amount,
                           @PathVariable Long itemId) {
        itemService.edit(itemId, storageId, amount);
        return "redirect:/items/" + itemId;
    }
}
