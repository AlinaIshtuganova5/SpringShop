package com.itis.shop.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 18.05.17.
 */
@Controller
public class UtilsController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getErrorPage(){
        return "error_page";
    }
}
