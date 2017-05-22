package com.itis.shop.business.utils;

import com.itis.shop.business.service.UserService;
import com.itis.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created on 17.05.17.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;


        if (userService.findUserbyEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicated email",
                    new Object[]{"'email'"}, "Email already exists");
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 16){
            errors.rejectValue("password", "password length",
                    new Object[]{"'password'"}, "Password must be 6 - 16");


        }
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff passwords",
                    new Object[]{"'confirmPassword'"}, "Password does not match the confirm password");

        }

    }
}