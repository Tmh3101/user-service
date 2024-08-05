package com.Tmh3101.user_manager.service;

import com.Tmh3101.user_manager.dto.request.UserCreationRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.entity.User;

import java.util.List;

public interface UserService {
    public List<UserResponse> getAllUser();
    public UserResponse getUserById(String id);
    public UserResponse createUser(UserCreationRequest userCreationRequest);
    public UserResponse updateUser(String idUser, UserCreationRequest userCreationRequest);
    public UserResponse deleteUserById(String id);
}
