package com.jobportal.controller;

import com.jobportal.Utils.JwtUtil;
import com.jobportal.dto.AuthRequest;
import com.jobportal.dto.UserRequest;
import com.jobportal.entity.User;
import com.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User createUser(@RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthRequest authRequest){
        Authentication auth = new UsernamePasswordAuthenticationToken(authRequest.getEmail() , authRequest.getPassword());
        return jwtUtil.generateToken(authRequest.getEmail());
    }
}
