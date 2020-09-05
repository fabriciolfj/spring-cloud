package com.github.fabriciolfj.photoAppApiUsers.api.model;

import lombok.Data;

@Data
public class LoginRequestModel {

    private String email;
    private String password;
}
