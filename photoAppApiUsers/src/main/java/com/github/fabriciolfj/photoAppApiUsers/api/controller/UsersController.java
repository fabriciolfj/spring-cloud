package com.github.fabriciolfj.photoAppApiUsers.api.controller;

import com.github.fabriciolfj.photoAppApiUsers.api.model.CreateUserRequestModel;
import com.github.fabriciolfj.photoAppApiUsers.api.model.CreateUserResponseModel;
import com.github.fabriciolfj.photoAppApiUsers.api.share.UserDTO;
import com.github.fabriciolfj.photoAppApiUsers.domain.entity.UserEntity;
import com.github.fabriciolfj.photoAppApiUsers.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment env;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port " + env.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid final CreateUserRequestModel model) {
        log.info("Request create user: " + model);
        final UserDTO user = userService.createUser(modelMapper.map(model, UserDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(user, CreateUserResponseModel.class));
    }
}
