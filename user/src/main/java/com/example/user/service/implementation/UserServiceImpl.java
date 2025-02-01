package com.example.user.service.implementation;

import com.example.model.UserModel;
import com.example.user.mappers.UserMapper;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserModel createUser(final UserModel user) {
        return userMapper.entityToModel(userRepository.save(userMapper.modelToEntity(user)));
    }
}
