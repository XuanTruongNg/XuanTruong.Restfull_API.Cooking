package com.XuanTruong.cooking.service;

import org.springframework.stereotype.Service;

@Service
public interface IMailService {
    void sendEmail(String to, String body);
}
