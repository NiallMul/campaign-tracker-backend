package com.example.user.service.implementation;

import com.example.model.UserModel;
import com.example.user.configuration.JwtUtil;
import com.example.user.mappers.UserMapper;
import com.example.user.repository.UserRepository;
import com.example.user.repository.dto.UserEntity;
import com.example.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
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
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public String createUser(final UserModel user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        UserModel newUser = userMapper.entityToModel(userRepository.save(userMapper.modelToEntity(user)));
        return jwtUtil.generateToken(newUser.getUsername());
    }

    @Override
    public String authenticate(final String email, final String password) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userExistsAndPasswordValid(password, userEntity)) {
            return jwtUtil.generateToken(email);
        }
        return new EntityNotFoundException("User not found").toString();
    }

    private boolean userExistsAndPasswordValid(String password, Optional<UserEntity> userEntity) {
        return userEntity.isPresent() && passwordEncoder.matches(password, userEntity.get().getPassword());
    }


}
