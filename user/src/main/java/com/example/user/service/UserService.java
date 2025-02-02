package com.example.user.service;

import com.example.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserModel createUser(UserModel user);

    Boolean authenticate(String email, String password);
}
