package com.example.user.mappers;

import com.example.model.UserModel;
import com.example.user.repository.dto.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "email", source = "username")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "password", source = "password")
    UserEntity modelToEntity(UserModel userModel);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "username", source = "email")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "uuid", source = "uid")
    UserModel entityToModel(UserEntity userEntity);
}
