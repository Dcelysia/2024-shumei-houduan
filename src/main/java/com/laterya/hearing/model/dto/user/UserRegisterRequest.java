package com.laterya.hearing.model.dto.user;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String userName;
    private String password;
    private String checkPassword;
    private String phone;
    private String code;
    private String sex;
}
