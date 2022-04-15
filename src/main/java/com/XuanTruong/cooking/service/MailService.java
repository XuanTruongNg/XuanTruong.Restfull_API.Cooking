package com.XuanTruong.cooking.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService  implements IMailService{

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String to, String body) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(to);
        simpleMessage.setSubject("Complete Registration!");
        simpleMessage.setText(body);
        javaMailSender.send(simpleMessage);
    }
}
