package com.itis.shop.business.service.impl;

import com.itis.shop.business.service.MailService;
import com.itis.shop.business.utils.EmailConfirmProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created on 17.05.17.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendConfirmationMail(String token, String email) {
        mailSender.send(createMessage(token, email));
    }

    private SimpleMailMessage createMessage(String token, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailConfirmProperties.Email);
        message.setSubject(EmailConfirmProperties.Subject);
        message.setText(EmailConfirmProperties.Message + " \r\n"
                + EmailConfirmProperties.ConfirmPath + token + "&email=" + email);
        message.setTo(email);
        return message;
    }
}
