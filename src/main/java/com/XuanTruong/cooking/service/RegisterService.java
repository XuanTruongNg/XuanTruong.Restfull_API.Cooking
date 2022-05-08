package com.XuanTruong.cooking.service;


import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.message.Status;
import com.XuanTruong.cooking.payload.RegistrationRequest;
import com.XuanTruong.cooking.reponsitory.IUserRepository;
import com.XuanTruong.cooking.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.XuanTruong.cooking.security.SecurityConstants.lINK_CONFIRMATION;

@Component
public class RegisterService implements IRegisterService{
    @Autowired
    private IMailService mailService;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public void sendEmail(String to, String jwt) {
        String body = lINK_CONFIRMATION + jwt;
        mailService.sendEmail(to,body);
    }
    @Override
    public void enableUser(String userName) {
        User user = userRepository.findByUserName(userName);
        user.setStatus(true);
        userRepository.save(user);
    }

    @Override
    public Boolean checkUserExist(String username) {
        return userService.IsUserExist(username);
    }

    @Override
    public Boolean checkEmailExist(String email) {
        return userService.IsEmailExist(email);
    }

    @Override
    public Status makeResponseForRegis(RegistrationRequest regist) {
        if(checkEmailExist(regist.getEmail())){
            return Status.MAIL_EXISTED ;
        }
        if(checkUserExist(regist.getUserName())){
            return Status.USER_NAME_EXISTED;
        }
        userService.createUser(regist);
        // tao token va dua cho nguoi
        String jwtConfirmation = tokenProvider.generateTokenForConfirmation(userService.getUserByUsername(regist.getUserName()));
        sendEmail(regist.getEmail(),jwtConfirmation);
        return Status.PLEASE_CHECK_YOUR_MAILS;

    }

    @Override
    public Status makeResponseForConfirmRegis(String token) {
        if(tokenProvider.validateToken(token)){
            String userName = tokenProvider.getUserName(token);
            enableUser(userName);
            return Status.SUCCESSFULL;
        }else {
            return Status.FAILS;
        }
    }


}
