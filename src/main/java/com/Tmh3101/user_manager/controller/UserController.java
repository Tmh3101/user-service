package com.Tmh3101.user_manager.controller;

import com.Tmh3101.user_manager.dto.request.ApiResponse;
import com.Tmh3101.user_manager.dto.request.UserRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    UserServiceImpl userServiceImpl;

    @GetMapping("")
    public ApiResponse<List<UserResponse>> getAllUser(){
        logInfor("get all users");
        return ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .result(userServiceImpl.getAllUser())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String id){
        logInfor("get user by id");
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userServiceImpl.getUserById(id))
                .build();
    }

    @GetMapping("/my-info")
    public ApiResponse<UserResponse> getMyInfo(){
        logInfor("get my information");
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userServiceImpl.getMyInfo())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest request){
        logInfor("create user");
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userServiceImpl.createUser(request))
                .build();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody @Valid UserRequest userCreationRequest){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userServiceImpl.updateUser(id, userCreationRequest))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<UserResponse> deleteUserById(@PathVariable String id){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userServiceImpl.deleteUserById(id))
                .build();
    }

    private void logInfor(String methodName){
        log.info("In method {}:", methodName);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User email: {}", authentication.getName());
        log.info("Role and Permisssion:");
        authentication.getAuthorities().forEach(grant ->
                log.info(grant.getAuthority())
        );
    }
}
