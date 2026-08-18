package com.authenticator.service;

import com.authenticator.dto.UserRequestDTO;
import com.authenticator.entity.Role;
import com.authenticator.entity.User;
import com.authenticator.exception.BadRequestException;
import com.authenticator.exception.InvalidUserException;
import com.authenticator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User register(UserRequestDTO userRequest){
        Optional<User> existing = userRepository.findByEmail(userRequest.getEmail());
        if (existing.isPresent()) {
            throw new BadRequestException("Email is already in use");
        }
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.GUEST);
        return userRepository.save(user);
    }
    public User login(UserRequestDTO userRequest){
        Optional<User> existing = userRepository.findByEmail(userRequest.getEmail());
        if(!existing.isPresent()) {
            throw new InvalidUserException("Please register");
        }
        String rawPass = userRequest.getPassword();
        User user = existing.get();
        String storedHash = user.getPassword();
        if(passwordEncoder.matches(rawPass,storedHash))
        {
            return user;
        }
        else {
            throw new InvalidUserException("Email/password does not match. Please try again");
        }
    }
}
