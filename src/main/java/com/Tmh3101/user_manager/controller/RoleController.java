package com.Tmh3101.user_manager.controller;

import com.Tmh3101.user_manager.dto.request.ApiResponse;
import com.Tmh3101.user_manager.dto.request.RoleRequest;
import com.Tmh3101.user_manager.dto.response.RoleResponse;
import com.Tmh3101.user_manager.service.Impl.RoleServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/roles")
public class RoleController {

    RoleServiceImpl roleServiceImpl;

    @PostMapping("/add")
    public ApiResponse<RoleResponse> addRole(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleServiceImpl.create(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<RoleResponse>> getAllRoles(){
        return ApiResponse.<List<RoleResponse>>builder()
                .code(1000)
                .result(roleServiceImpl.getAll())
                .build();
    }

    @DeleteMapping("/delete/{roleName}")
    public ApiResponse<RoleResponse> deletePermission(@PathVariable String roleName){
        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleServiceImpl.delete(roleName))
                .build();
    }

}
