package com.github.fabriciolfj.photoAppApiUsers.api.share;

import com.github.fabriciolfj.photoAppApiUsers.api.model.AlbumResponseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 5843847243414526254L;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userId;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;
}
