package com.example.gateway.controller.user;

import com.example.gateway.facade.UserFacade;
import com.example.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserGatewayController {

    private final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<String> createUser(final @RequestBody UserModel user) {
        return ResponseEntity.ok(userFacade.createUser(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(final @RequestBody UserModel user) {
        return ResponseEntity.ok(userFacade.authenticate(user));
    }
}
