package com.itis.shop.business.controller;

import com.itis.shop.business.exceptions.SpringShopException;
import com.itis.shop.business.service.MailService;
import com.itis.shop.business.service.UserService;
import com.itis.shop.business.utils.UserValidator;
import com.itis.shop.dto.UserDto;
import com.itis.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created on 17.05.17.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (userService.getCurrentUser() != null)
            return "redirect:/user";
        return "login";
    }

    @RequestMapping(value = {"/signup", "/signUp", "sign_up"}, method = RequestMethod.GET)
    public String signUp(Model model) {
        if (userService.getCurrentUser() != null)
            return "redirect:/user";
        model.addAttribute("user", new UserDto());
        return "sign_up";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sign_up";
        }
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "sign_up";
        }
        user = userService.signUp(user);

        if (user.getId() != null) {
            mailService.sendConfirmationMail(user.getToken(), user.getEmail());
            model.addAttribute("message", "Account confirmation email was sent))");
            return "ok_page";
        }
        throw new SpringShopException();
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmAccount(@RequestParam String token, @RequestParam String email, Model model) {
        if (userService.confirmAccount(token, email)) {
            model.addAttribute("message", "Account confirmed");
            return "ok_page";
        }
        throw new SpringShopException();
    }
}
