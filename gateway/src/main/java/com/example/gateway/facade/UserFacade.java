package com.example.gateway.facade;

import com.example.client.UserServiceClient;
import com.example.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserServiceClient userServiceClient;

    public UserModel createUser(final UserModel user) {
        return userServiceClient.createUser(user).getBody();
    }

    public Boolean authenticate(final UserModel user) {
        return userServiceClient.authenticate(user).getBody();
    }
}
