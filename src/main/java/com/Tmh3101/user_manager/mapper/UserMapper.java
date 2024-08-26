package com.Tmh3101.user_manager.mapper;

import com.Tmh3101.user_manager.dto.request.UserRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequest userCreationRequest);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserRequest userCreationRequest);

    UserResponse toUserResponse(User user);
}
