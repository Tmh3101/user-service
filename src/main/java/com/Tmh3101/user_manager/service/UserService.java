package com.Tmh3101.user_manager.service;

import com.Tmh3101.user_manager.dto.request.UserRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();
    UserResponse getUserById(String id);
    UserResponse createUser(UserRequest userCreationRequest);
    UserResponse updateUser(String idUser, UserRequest userCreationRequest);
    UserResponse deleteUserById(String id);
}
