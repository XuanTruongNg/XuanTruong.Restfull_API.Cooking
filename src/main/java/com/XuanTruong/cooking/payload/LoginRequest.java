package com.XuanTruong.cooking.payload;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String passWord;
}
