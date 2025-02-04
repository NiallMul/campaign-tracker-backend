package com.example.client;

import com.example.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "localhost:8081/user")
public interface UserServiceClient {
    @PostMapping
    ResponseEntity<?> createUser(final @RequestBody UserModel user);

    @PostMapping("/authenticate")
    ResponseEntity<?> authenticate(final @RequestBody UserModel user);
}
