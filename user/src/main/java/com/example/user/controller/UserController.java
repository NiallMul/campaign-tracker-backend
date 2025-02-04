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

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserServiceClient {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(final @RequestBody UserModel user) {
        final String jwtToken = userService.createUser(user);
        return ResponseEntity.ok(Map.of("Bearer", jwtToken));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(final @RequestBody UserModel user) {
        final String jwtToken = userService.authenticate(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(Map.of("Bearer", jwtToken));
    }
}
