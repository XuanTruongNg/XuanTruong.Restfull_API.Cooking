package com.XuanTruong.cooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class CookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CookingApplication.class, args);
    }

}
