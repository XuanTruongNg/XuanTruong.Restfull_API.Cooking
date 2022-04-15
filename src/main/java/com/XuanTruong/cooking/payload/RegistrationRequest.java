package com.XuanTruong.cooking.payload;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String userName;
    private String email;
    private String passWord;
    private String fullName;

}
