package com.XuanTruong.cooking.service;


import com.XuanTruong.cooking.payload.RegistrationRequest;
import com.XuanTruong.cooking.payload.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface IRegisterService {
    void sendEmail(String to, String jwt);
    void enableUser(int userId);
    Boolean checkUserExist( String username);
    Boolean checkEmailExist( String email);
    RegistrationResponse makeResponseForRegis( RegistrationRequest regist);
    RegistrationResponse makeResponseForConfirmRegis(String token);

}
