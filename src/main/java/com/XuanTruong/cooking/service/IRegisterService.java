package com.XuanTruong.cooking.service;


import com.XuanTruong.cooking.message.Status;
import com.XuanTruong.cooking.payload.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface IRegisterService {
    void sendEmail(String to, String jwt);
    void enableUser(String userName);
    Boolean checkUserExist( String username);
    Boolean checkEmailExist( String email);
    Status makeResponseForRegis(RegistrationRequest regist);
    Status makeResponseForConfirmRegis(String token);

}
