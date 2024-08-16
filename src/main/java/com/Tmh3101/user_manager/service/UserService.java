package com.Tmh3101.user_manager.service;

import com.Tmh3101.user_manager.dto.request.UserCreationRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse getUserById(String id);
    UserResponse createUser(UserCreationRequest userCreationRequest);
    UserResponse updateUser(String idUser, UserCreationRequest userCreationRequest);
    UserResponse deleteUserById(String id);
}
