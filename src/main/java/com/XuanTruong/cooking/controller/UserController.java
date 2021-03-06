package com.XuanTruong.cooking.controller;
import com.XuanTruong.cooking.entity.CustomUserDetails;
import com.XuanTruong.cooking.entity.User;
import com.XuanTruong.cooking.message.Status;
import com.XuanTruong.cooking.payload.LoginRequest;
import com.XuanTruong.cooking.payload.LoginResponse;
import com.XuanTruong.cooking.payload.RegistrationRequest;
import com.XuanTruong.cooking.security.JwtTokenProvider;
import com.XuanTruong.cooking.service.IRegisterService;
import com.XuanTruong.cooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        private UserService userService;
        @Autowired
        private JwtTokenProvider tokenProvider;
        @Autowired
        private IRegisterService registerService;

        @PostMapping ("/login1")
        public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

            // Xác thực từ username và password.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassWord()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Integer userId = userService.getUserByUsername(loginRequest.getUserName()).getUserId();
            List<String> role = userService.getRoleByUserId(userId);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal(),role);
            System.out.println(jwt);
            return new LoginResponse(jwt);
        }

        // registration
        @PostMapping("/registration")
        public Status registration(@RequestBody RegistrationRequest regist){
            return registerService.makeResponseForRegis(regist);
        }
        @GetMapping("/confirm")
        public Status comfirm(@RequestParam String token){
            return registerService.makeResponseForConfirmRegis(token);
        }
        @GetMapping("/hello")
        public String hello(){
            return "hello";
        }

    }

