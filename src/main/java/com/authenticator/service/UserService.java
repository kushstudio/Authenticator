package com.authenticator.service;
import com.authenticator.dto.UserRequestDTO;
import com.authenticator.entity.User;

public interface UserService {
    User register(UserRequestDTO userRequestDTO);
    String login(UserRequestDTO userRequestDTO);
}
