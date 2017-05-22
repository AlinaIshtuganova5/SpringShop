package com.itis.shop.business.controller;

import com.itis.shop.business.dao.ItemDao;
import com.itis.shop.business.exceptions.SpringShopException;
import com.itis.shop.business.service.ItemService;
import com.itis.shop.business.service.StorageService;
import com.itis.shop.business.service.UserService;
import com.itis.shop.model.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;

/**
 * Created on 17.05.17.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/item/search", method = RequestMethod.POST)
    public String search(@RequestParam(required = false) String search, RedirectAttributes attributes) throws InterruptedException {
        if (search != null && !StringUtils.isBlank(search)) {
            search = new String(search.getBytes(StandardCharsets.ISO_8859_1));
            attributes.addFlashAttribute("items", itemDao.search(search));
        }
        if (search != null || !StringUtils.isBlank(search))
            attributes.addFlashAttribute("items", itemDao.search(search));
        return "redirect:/";
    }

    @RequestMapping(value = "/item/group", method = RequestMethod.POST)
    public String group(@RequestParam(name = "group_id") Long groupId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("items", itemService.groupBy(groupId));
        return "redirect:/";
    }

    @RequestMapping(value = "/add/item/{itemId}", method = RequestMethod.POST)
    public String addItem(@PathVariable Long itemId,
                          @RequestParam(name = "view") String view, Model model) {
        userService.addItem(itemId, view);
        if (view.equals("/items/"))
            view += itemId;
        return "redirect:" + view;
    }

    @RequestMapping(value = {"/items", "/"}, method = RequestMethod.GET)
    public String getItems(Model model) {
        if (model.asMap().isEmpty())
            model.addAttribute("items", itemService.findAll());
        model.addAttribute("groups", itemService.getGroups());
        return "items";
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.GET)
    public String getItem(@PathVariable Long itemId, Model model) {
        Item item = itemService.find(itemId);
        if (item == null) throw new SpringShopException();
        model.addAttribute("item", item);
        model.addAttribute("storages", storageService.findAll());
        return "item";
    }
}
