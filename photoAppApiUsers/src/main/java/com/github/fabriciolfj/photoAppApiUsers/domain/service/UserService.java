package com.github.fabriciolfj.photoAppApiUsers.domain.service;


import com.github.fabriciolfj.photoAppApiUsers.api.share.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO createUser(final UserDTO userDetails);
    UserDTO getUserDetailsByEmail(final String email);
}
