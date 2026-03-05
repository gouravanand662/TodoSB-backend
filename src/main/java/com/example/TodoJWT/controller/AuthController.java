package com.example.TodoJWT.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.TodoJWT.entity.User;
import com.example.TodoJWT.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Register API
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {

        User savedUser = authService.register(user);

        Map<String, Object> response = new HashMap<>();
        response.put("id", savedUser.getId());
        response.put("username", savedUser.getUsername());
        response.put("role", savedUser.getRole());
        response.put("message", "User registered successfully");

        return response;
    }

    // Login API
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        String token = authService.login(user.getUsername(), user.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);  //key-value pair

        return response;
    }
}