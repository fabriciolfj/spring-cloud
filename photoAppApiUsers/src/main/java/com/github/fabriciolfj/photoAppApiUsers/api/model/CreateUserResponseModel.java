package com.github.fabriciolfj.photoAppApiUsers.api.model;

import lombok.Data;

@Data
public class CreateUserResponseModel {

    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}