package com.example.user.service.implementation;

import com.example.model.UserModel;
import com.example.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Boolean createUser(final UserModel user) {
        return false;
    }
}
