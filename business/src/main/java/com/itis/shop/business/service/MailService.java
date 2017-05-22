package com.itis.shop.business.service;

/**
 * Created on 17.05.17.
 */
public interface MailService {

    void sendConfirmationMail(String token, String email);
}
