package com.Tmh3101.user_manager.service.Impl;


import com.Tmh3101.user_manager.dto.request.UserCreationRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.entity.User;
import com.Tmh3101.user_manager.exception.AppException;
import com.Tmh3101.user_manager.exception.ErrorCode;
import com.Tmh3101.user_manager.mapper.UserMapper;
import com.Tmh3101.user_manager.repo.UserRepo;
import com.Tmh3101.user_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepo userRepo;
    @Autowired
    public UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepo.findAll();
        if(users.isEmpty())
            throw new AppException(ErrorCode.NOT_FOUND_ANY_USERS);

        List<UserResponse> result = new ArrayList<>();
        users.forEach((user) ->
                result.addLast(userMapper.toUserResponse(user))
        );
        return result;
    }

    @Override
    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(getUser(id));
    }

    @Override
    public UserResponse createUser(UserCreationRequest userCreationRequest) {

        if(userRepo.existsByEmail(userCreationRequest.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        if(userRepo.existsByPhoneNumber(userCreationRequest.getPhoneNumber()))
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTED);

        User user = userMapper.toUser(userCreationRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepo.save(user));
    }

    @Override
    public UserResponse updateUser(String idUser, UserCreationRequest userCreationRequest){
        User user = getUser(idUser);
        return userMapper.toUserResponse(userRepo.save(user));
    }

    @Override
    public UserResponse deleteUserById(String id) {
        User result = getUser(id);
        userRepo.deleteById(id);
        return userMapper.toUserResponse(userRepo.save(result));
    }

    public User getUser(String id){
        return userRepo.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ANY_USERS));
    }

}
