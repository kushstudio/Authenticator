package com.authenticator.controller;

import com.authenticator.dto.UserRequestDTO;
import com.authenticator.entity.User;
import com.authenticator.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserRequestDTO userRequest) {
        User newUser = userService.register(userRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login (@Valid @RequestBody UserRequestDTO userRequest) {
        String existing = userService.login(userRequest);
        return new ResponseEntity<>(existing, HttpStatus.OK);
    }
}
