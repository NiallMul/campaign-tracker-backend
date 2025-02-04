package com.example.gateway.facade;

import com.example.client.UserServiceClient;
import com.example.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserServiceClient userServiceClient;

    public Object createUser(final UserModel user) {
        return userServiceClient.createUser(user).getBody();
    }

    public Object authenticate(final UserModel user) {
        return userServiceClient.authenticate(user).getBody();
    }
}
