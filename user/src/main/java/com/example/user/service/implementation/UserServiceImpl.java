package com.example.user.service.implementation;

import com.example.model.UserModel;
import com.example.user.mappers.UserMapper;
import com.example.user.repository.UserRepository;
import com.example.user.repository.dto.UserEntity;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserModel createUser(final UserModel user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userMapper.entityToModel(userRepository.save(userMapper.modelToEntity(user)));
    }

    @Override
    public Boolean authenticate(final String email, final String password) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.isPresent() && passwordEncoder.matches(password, userEntity.get().getPassword());
    }


}
