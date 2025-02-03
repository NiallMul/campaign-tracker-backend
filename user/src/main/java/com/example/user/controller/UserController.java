package com.example.user.controller;

import com.example.client.UserServiceClient;
import com.example.model.UserModel;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserServiceClient {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(final @RequestBody UserModel user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(final @RequestBody UserModel user) {
        return ResponseEntity.ok(userService.authenticate(user.getUsername(), user.getPassword()));
    }
}
