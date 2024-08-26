package com.Tmh3101.user_manager.service.Impl;

import com.Tmh3101.user_manager.dto.request.UserRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.entity.Role;
import com.Tmh3101.user_manager.entity.User;
import com.Tmh3101.user_manager.exception.AppException;
import com.Tmh3101.user_manager.exception.ErrorCode;
import com.Tmh3101.user_manager.mapper.UserMapper;
import com.Tmh3101.user_manager.repo.RoleRepo;
import com.Tmh3101.user_manager.repo.UserRepo;
import com.Tmh3101.user_manager.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    RoleRepo roleRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
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
    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse getUserById(String id) {;
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
    public UserResponse createUser(UserRequest request) {

        if(userRepo.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        if(userRepo.existsByPhoneNumber(request.getPhoneNumber()))
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<String> rolesName = request.getRoles();

        if(rolesName.isEmpty())
            rolesName.add("USER");

        List<Role> roles = new ArrayList<>();
        rolesName.forEach(role ->
            roles.add(roleRepo.findRoleByName(role)
                .orElseThrow(() ->
                        new AppException(ErrorCode.NOT_FOUND_ANY_ROLES))
                )
            );

        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserResponse(userRepo.save(user));
    }

    @Override
    @PreAuthorize("returnObject.email == authentication.name")
    public UserResponse updateUser(String idUser, UserRequest request){
        User user = getUser(idUser);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Role> roles = roleRepo.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepo.save(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
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
