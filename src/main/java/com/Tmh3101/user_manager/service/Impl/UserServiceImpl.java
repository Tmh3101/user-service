package com.Tmh3101.user_manager.service.Impl;

import com.Tmh3101.user_manager.dto.request.UserCreationRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.entity.User;
import com.Tmh3101.user_manager.enums.Role;
import com.Tmh3101.user_manager.exception.AppException;
import com.Tmh3101.user_manager.exception.ErrorCode;
import com.Tmh3101.user_manager.mapper.UserMapper;
import com.Tmh3101.user_manager.repo.UserRepo;
import com.Tmh3101.user_manager.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    UserRepo userRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser() {
        log.info("In get all user method");
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
    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(getUser(id));
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        var email = context.getAuthentication().getName();
        User user = userRepo.findUserByEmail(email).orElseThrow(() ->
                new AppException(ErrorCode.NOT_FOUND_ANY_USERS)
        );
        return userMapper.toUserResponse(user);
    }


    @Override
    public UserResponse createUser(UserCreationRequest userCreationRequest) {

        if(userRepo.existsByEmail(userCreationRequest.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        if(userRepo.existsByPhoneNumber(userCreationRequest.getPhoneNumber()))
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTED);

        User user = userMapper.toUser(userCreationRequest);

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
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
        return userRepo.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.NOT_FOUND_ANY_USERS)
        );
    }
}
