package com.example.user.controller;

import com.example.model.UserModel;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Boolean> createUser(final UserModel user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}
