package com.example.user.service;

import com.example.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String createUser(final UserModel user);

    String authenticate(final String email, final String password);
}
