package com.github.fabriciolfj.photoAppApiUsers.domain.service.impl;

import com.github.fabriciolfj.photoAppApiUsers.api.share.UserDTO;
import com.github.fabriciolfj.photoAppApiUsers.domain.entity.UserEntity;
import com.github.fabriciolfj.photoAppApiUsers.domain.repository.UserRepository;
import com.github.fabriciolfj.photoAppApiUsers.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(final UserDTO userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        return modelMapper.map(userRepository.save(modelMapper.map(userDetails, UserEntity.class)), UserDTO.class);
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(u -> modelMapper.map(u, UserDTO.class))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(u -> new User(u.getEmail(), u.getEncryptedPassword(), true, true, true, true, new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
