package com.example.user.service;

import com.example.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserModel createUser(final UserModel user);

    Boolean authenticate(final String email, final String password);
}
