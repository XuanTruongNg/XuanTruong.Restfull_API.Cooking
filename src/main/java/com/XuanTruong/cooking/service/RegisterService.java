package com.XuanTruong.cooking.service;


import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.payload.RegistrationRequest;
import com.XuanTruong.cooking.payload.RegistrationResponse;
import com.XuanTruong.cooking.reponsitory.IUserRepository;
import com.XuanTruong.cooking.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegisterService implements IRegisterService{
    private final String linkConfirm;
    @Autowired
    private IMailService mailService;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    public RegisterService(@Value("${lINK_CONFIRMATION}") String linkConfirm) {
        this.linkConfirm = linkConfirm;
    }

    @Override
    public void sendEmail(String to, String jwt) {
        String body = linkConfirm + jwt;
        mailService.sendEmail(to,body);
    }
    @Override
    public void enableUser(int userId) {
        User user = userRepository.findUsersById(userId);
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
    public RegistrationResponse makeResponseForRegis(RegistrationRequest regist) {
        if(checkEmailExist(regist.getEmail())){
            return new RegistrationResponse("Email already is existed");
        }
        if(checkUserExist(regist.getUserName())){
            return new RegistrationResponse("username already is existed");
        }
        userService.createUser(regist);
        // tao token va dua cho nguoi
        String jwtConfirmation = tokenProvider.generateTokenForConfirmation(userService.getUserByUsername(regist.getUserName()));
        sendEmail(regist.getEmail(),jwtConfirmation);
        return new RegistrationResponse("please check your email and enable your account");

    }

    @Override
    public RegistrationResponse makeResponseForConfirmRegis(String token) {
        if(tokenProvider.validateToken(token)){
            int userId = tokenProvider.getUserIdFromJWT(token);
            enableUser(userId);
            return new RegistrationResponse("welcome to cooking app");
        }else {
            return new RegistrationResponse("sorry confirmation timeout");
        }
    }


}
