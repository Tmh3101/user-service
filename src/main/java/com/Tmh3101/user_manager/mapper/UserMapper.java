package com.Tmh3101.user_manager.mapper;

import com.Tmh3101.user_manager.dto.request.UserCreationRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserCreationRequest userCreationRequest);
    public void updateUser(@MappingTarget User user, UserCreationRequest userCreationRequest);
    public UserResponse toUserResponse(User user);
}
