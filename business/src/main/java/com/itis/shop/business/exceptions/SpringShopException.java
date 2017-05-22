package com.itis.shop.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created on 18.05.17.
 */

public class SpringShopException extends RuntimeException {

    @Override
    public String getMessage() {
       return  "Oops, something went wrong((";
    }
}
