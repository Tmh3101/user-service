package com.Tmh3101.user_manager.controller;

import com.Tmh3101.user_manager.dto.request.ApiResponse;
import com.Tmh3101.user_manager.dto.request.UserCreationRequest;
import com.Tmh3101.user_manager.dto.response.UserResponse;
import com.Tmh3101.user_manager.service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserServiceImpl userServiceImpl;

    @GetMapping("")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userServiceImpl.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userServiceImpl.getUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest){
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                                        .code(1000)
                                        .result(userServiceImpl.createUser(userCreationRequest))
                                        .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody @Valid UserCreationRequest userCreationRequest){
        return ResponseEntity.ok(userServiceImpl.updateUser(id, userCreationRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id){
        return ResponseEntity.ok(userServiceImpl.deleteUserById(id));
    }
}
